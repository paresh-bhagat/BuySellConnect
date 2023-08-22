package com.BuySellConnect.web.controller;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.service.UserService;

@Controller
@RequestMapping("/BuySellConnect/user")
public class ProfileController {

	@Autowired
	private UserService userservice;
	
	//profile page handler
	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String profile(Principal principal,Model model) {
		String name = principal.getName();
		System.out.println("This is the profile page");
		model.addAttribute("username", name);
        return "profile";
	}
	
	// delete account
	@RequestMapping(value="/deleteaccount",method = RequestMethod.GET)
	public String deleteAccount(Principal principal,Model model) throws Exception {
		System.out.println("This is delete account page");
		String name = principal.getName();
		UserInfo user = this.userservice.getUserInfo(name);
		this.userservice.deleteAccount(user);
		return "redirect:/logout";
	}
	
	// change password
	@RequestMapping(value="/changepassword",method = RequestMethod.GET)
	public String changePassword(Model model) throws Exception {
		System.out.println("This is change password page");
		model.addAttribute("userInfo", new UserInfo());
		return "changepassword";
	}
	
	// process change password form
	@RequestMapping(path="/processchangepasswordform", method=RequestMethod.POST)
	public String handleChangePasswordForm(@Valid @ModelAttribute("userInfo") UserInfo user,
			@RequestParam("newpasswordfirst") String newPasswordFirst,
			@RequestParam("newpasswordsecond") String newPasswordSecond,
			BindingResult result, HttpSession session, Model model,
			Principal principal) throws IOException {
					
		System.out.println(result.hasErrors());
		System.out.println("This is the change password form handler");
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("userInfo", user);
			return "changepassword";
		}
		
		String name = principal.getName();
		
		if(!this.userservice.checkPassword(name,user.getPassword())) {
			System.out.println("password wrong");
			model.addAttribute("errorMessage", "Wrong Password !");
			model.addAttribute("userInfo", user);
			return "changepassword";
		}
		
		if( newPasswordFirst.length()==0 || newPasswordFirst.length()>20 || 
				newPasswordSecond.length()==0 || newPasswordSecond.length()>20 ) {
			System.out.println("new password does not match");
			model.addAttribute("errorMessage", "New Password must be between 1-20 characters!");
			model.addAttribute("userInfo", user);
			return "changepassword";
		}
		
		if(!newPasswordFirst.equals(newPasswordSecond)) {
			System.out.println("new password does not match");
			model.addAttribute("errorMessage", "New and Retyped Password does not match!");
			model.addAttribute("userInfo", user);
			return "changepassword";
		}
						
		this.userservice.changePassword(name, newPasswordSecond);
					
		model.addAttribute("changepasswordsuccessful", "Password Changed Successfully");
		return "profile"; 
	}
}
