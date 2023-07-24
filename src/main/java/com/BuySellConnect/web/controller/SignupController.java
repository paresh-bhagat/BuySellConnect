package com.BuySellConnect.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {
	
		// signup page
		@RequestMapping(value="/BuySellConnect/signup",method = RequestMethod.GET)
		public String signupHandler() {
			System.out.println("This is the signup page");
			return "signup";
		}
		
		// signup form
		@RequestMapping(path="/processsignupform", method=RequestMethod.POST)
		public String handleSignUpForm(@RequestParam("user_name") String user_name,
				@RequestParam("user_password") String user_password, 
				@RequestParam("user_mobile_number") String user_mobile_number ) {
			
			return "redirect:/BuySellConnect/enterotp";
			
		}
		
}
