package com.BuySellConnect.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.BuySellConnect.web.entities.UserInfo;

@Controller
@RequestMapping("/BuySellConnect")
public class LoginController {
	
		// login page
		@RequestMapping(value="/login",method = RequestMethod.GET)
		public String loginHandler(Model model) {
			model.addAttribute("user", new UserInfo());
			System.out.println("This is the login page");
			return "login";
		}
		
		/*// login form
		@RequestMapping(path="/processloginform", method=RequestMethod.POST)
		public String handleLogInForm(@Valid @ModelAttribute("user") UserInfo user, 
				BindingResult result) {
			
			System.out.println(result.hasErrors());
			
			if(result.hasErrors()) {
				System.out.println(result.getAllErrors());
				return "login";
			}
				
			//System.out.println(user.getUser_name());
			//System.out.println(user.getUser_password());
			return "redirect:/BuySellConnect/products";
		}
		
		// login via otp page
		@RequestMapping(value="/otplogin",method = RequestMethod.GET)
		public String loginviaOTPHandler() {
			
			System.out.println("This is the login otp page");
			return "otplogin";
		}
		
		// login via otp form
		@RequestMapping(path="/processloginviaotpform", method=RequestMethod.POST)
		public String handleLoginViaOtpForm(@RequestParam("number") String mobile_number) {
			
			if( this.loginservice.isValidMobileNo(mobile_number)==false )
				return "otplogin";
						
			return "redirect:/BuySellConnect/enterotp";
		}*/
		
}
