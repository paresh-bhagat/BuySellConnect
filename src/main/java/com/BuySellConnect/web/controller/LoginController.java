package com.BuySellConnect.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.service.UserService;
import com.BuySellConnect.web.service.emailService;
import com.BuySellConnect.web.service.smsService;

@Controller
@RequestMapping("/BuySellConnect")
public class LoginController {
		
		@Autowired
		private smsService smsservice;
		
		@Autowired
		private UserService userservice;
		
		@Autowired
	    private emailService emailservice;
		
		// login page
		@RequestMapping(value="/login",method = RequestMethod.GET)
		public String loginHandler(Model model) {
		
			model.addAttribute("user", new UserInfo());
			System.out.println("This is the login page");
			return "login";
		}
		
		// forgot password page
		@RequestMapping(value="/forgotpassword",method = RequestMethod.GET)
		public String forgotPassword(Model model) {
			
			System.out.println("This is the forgot password page");
			model.addAttribute("userInfo", new UserInfo());
			return "forgotpassword";
		}
		
		// forgot password form
		@RequestMapping(path="/processforgotpasswordform", method=RequestMethod.POST)
		public String handleForgotPasswordForm(@Valid @ModelAttribute("userInfo") UserInfo user,
				BindingResult result, HttpSession session, Model model) {
			
			System.out.println("This is the forgot password form handler");
			System.out.println(result.hasErrors());
			
			if(result.hasErrors()) {
				System.out.println(result.getAllErrors());
				model.addAttribute("userInfo", user);
				return "forgotpassword";
			}
			
			UserInfo useroriginal = new UserInfo();
			
			if(userservice.checkUserName(user.getUsername())) {
				System.out.println("Username exist");
				useroriginal = this.userservice.getUserInfo(user.getUsername());
				System.out.println(useroriginal);
				
			}
			else if(userservice.checkEmail(user.getEmail())) {
				System.out.println("Email already exist");
				useroriginal = this.userservice.getInfobyEmail(user.getEmail());
			}
			else {
				System.out.println("nothing exist");
				model.addAttribute("errorMessage", "No registered username or Email found!");
				model.addAttribute("userInfo", user);
				return "forgotpassword";
			}
				
			int attempts = 3;
			
			// send otp to email
			int[] otpEmail = this.smsservice.getOtp();
			
			Boolean sendOtpStatusEmail =  this.emailservice.sendForgotPasswordOTPEmail(useroriginal.getUsername(), 
					useroriginal.getEmail(),otpEmail);
			
			if(sendOtpStatusEmail==false) {
				System.out.println("Otp not send");
				model.addAttribute("errorMessage", "Send OTP failed.Please try again!");
				return "forgotpassword";
			}
				
			session.setAttribute("user", useroriginal.getUsername());
			session.setAttribute("otpemail",otpEmail);
			session.setAttribute("attempts", attempts);
			
			return "redirect:/BuySellConnect/emailotp";
						
		}
		
}
