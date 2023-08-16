package com.BuySellConnect.web.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.service.UserService;

@Controller
@RequestMapping("/BuySellConnect/user")
public class ProfileController {

	@Autowired
	private UserService userservice;
	
	//profile page handler
	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String profile() {
		System.out.println("This is the profile page");
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
}
