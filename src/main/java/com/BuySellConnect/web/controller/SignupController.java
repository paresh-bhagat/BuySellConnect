package com.BuySellConnect.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.service.SignupService;

@Controller
@RequestMapping("/BuySellConnect")
public class SignupController {
	
		@Autowired
		private SignupService signupservice;
		
		//@Autowired
		//private BCryptPasswordEncoder passswordEncoder;
	
		// signup page
		@RequestMapping(value="/signup",method = RequestMethod.GET)
		public String signupHandler(Model model) {
			System.out.println("This is the signup page");
			model.addAttribute("user", new UserInfo());
			return "signup";
		}
		
		// signup form
		@RequestMapping(path="/processsignupform", method=RequestMethod.POST)
		public String handleSignUpForm(@jakarta.validation.Valid @ModelAttribute("user") UserInfo user,
				@RequestParam("number") String mobile_number, BindingResult result) {
			
			System.out.println(result.hasErrors());
			
			if(result.hasErrors()) {
				System.out.println(result.getAllErrors());
				return "signup";
			}
			
			if(this.signupservice.isValidMobileNo(mobile_number)==false)
				return "redirect:/BuySellConnect/signup";
			
			//if(signupservice.checkUserName(user.getUser_name()))
			//	return "redirect:/BuySellConnect/signup";
			
			if(signupservice.checkMobileNumber(mobile_number))
				return "redirect:/BuySellConnect/signup";
			
			System.out.println("This is the signup form handler");
			//System.out.println(user.getUser_name());
			//System.out.println(user.getUser_password());
			//System.out.println(user.getUser_mobile_number());
			System.out.println(mobile_number);
			return "redirect:/BuySellConnect/enterotp"; 
		}
		
}  
		
