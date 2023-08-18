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
import com.BuySellConnect.web.service.SignupService;
import com.BuySellConnect.web.service.UserService;
import com.BuySellConnect.web.service.otpService;

@Controller
@RequestMapping("/BuySellConnect")
public class LoginController {
		
		@Autowired
		private SignupService signupservice;
	
		@Autowired
		private otpService otpservice;
		
		@Autowired
		private UserService userservice;

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
			
			if(signupservice.checkUserName(user.getUsername())) {
				System.out.println("Username exist");
				useroriginal = this.userservice.getUserInfo(user.getUsername());
				System.out.println(useroriginal);
				
			}
			else if(signupservice.checkEmail(user.getEmail())) {
				System.out.println("Email already exist");
				useroriginal = this.signupservice.getInfobyEmail(user.getEmail());
			}
			else {
				System.out.println("nothing exist");
				model.addAttribute("errorMessage", "No registered username or Email found!");
				model.addAttribute("userInfo", user);
				return "forgotpassword";
			}
				
			int attempts = 3;
			
			// send otp to email
			int[] otpEmail = otpservice.getOtp();
			Boolean sendOtpStatusEmail =  true;
			
			otpEmail[0]=0; otpEmail[1]=0;otpEmail[2]=0;otpEmail[3]=0;
			
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
