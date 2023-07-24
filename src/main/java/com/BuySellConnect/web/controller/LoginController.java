package com.BuySellConnect.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
		// login page
		@RequestMapping(value="/BuySellConnect/login",method = RequestMethod.GET)
		public String loginHandler() {
			System.out.println("This is the login page");
			return "login";
		}
		
		// login form
		@RequestMapping(path="/processloginform", method=RequestMethod.POST)
		public String handleLogInForm(@RequestParam("user_name") String user_name,
				@RequestParam("user_password") String user_password) {
					
			return "redirect:/BuySellConnect/products";
		}
		
		// login via otp page
		@RequestMapping(value="/BuySellConnect/otplogin",method = RequestMethod.GET)
		public String loginviaOTPHandler() {
			
			System.out.println("This is the login page");
			return "login";
		}
		
		// login via otp form
		@RequestMapping(path="/processloginviaotpform", method=RequestMethod.POST)
		public String handleLoginViaOtpForm(@RequestParam("user_name") String user_name,
				@RequestParam("user_password") String user_password) {
							
			return "redirect:/BuySellConnect/enterotp";
		}
		
		// otp page
		@RequestMapping(value="/BuySellConnect/enterotp",method = RequestMethod.GET)
		public String otpHandler() {
			System.out.println("This is the otp page");
			return "otp";
		}
		
		// otp form
		@RequestMapping(path="/processotpform", method=RequestMethod.POST)
		public String handleLoginViaOtpForm() {
							
			return "redirect:/BuySellConnect/products";
		}
		
}
