package com.BuySellConnect.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.service.SignupService;
import com.BuySellConnect.web.service.otpService;

@Controller
@RequestMapping("/BuySellConnect")
public class SignupController {
	
		@Autowired
		private SignupService signupservice;
		
		@Autowired
		private otpService otpservice;
		
		@Autowired
		private BCryptPasswordEncoder passswordEncoder;
	
		// signup page
		@RequestMapping(value="/signup",method = RequestMethod.GET)
		public String signupHandler(Model model) {
			System.out.println("This is the signup page");
			model.addAttribute("userInfo", new UserInfo());
			return "signup";
		}
		
		// signup form
		@RequestMapping(path="/processsignupform", method=RequestMethod.POST)
		public String handleSignUpForm(@Valid @ModelAttribute("userInfo") UserInfo user,
				BindingResult result, HttpSession session, Model model) throws IOException {
			
			System.out.println(result.hasErrors());
			
			if(result.hasErrors()) {
				System.out.println(result.getAllErrors());
				model.addAttribute("userInfo", user);
				return "signup";
			}
			
			if(signupservice.checkUserName(user.getUsername())) {
				System.out.println("Username already exist");
				model.addAttribute("errorMessage", "Username already exist!");
				model.addAttribute("userInfo", user);
				return "signup";
			}
				
			if(signupservice.checkMobileNumber(user.getMobileNumber())) {
				System.out.println("phonenumber already exist");
				model.addAttribute("errorMessage", "Mobile number already exist!");
				model.addAttribute("userInfo", user);
				return "signup";
			}
			
			if(signupservice.checkEmail(user.getEmail())) {
				System.out.println("Email already exist");
				model.addAttribute("errorMessage", "Email already exist!");
				model.addAttribute("userInfo", user);
				return "signup";
			}
				
			System.out.println("This is the signup form handler");
			System.out.println(user.getUsername());
			System.out.println( passswordEncoder.encode(user.getPassword()));
			System.out.println(user.getMobileNumber());
			String password = user.getPassword();
			
			user.setPassword(passswordEncoder.encode(password));
			user.setRole("ROLE_USER");
			int attempts = 3;
			
			// send otp
			int[] otp = otpservice.getOtp();
			Boolean sendOtpStatus =  true; //otpservice.sendOtpSms(user.getMobileNumber(), otp);
			
			otp[0]=0; otp[1]=0;otp[2]=0;otp[3]=0;
			
			if(sendOtpStatus==false) {
				System.out.println("Otp not send");
				model.addAttribute("errorMessage", "Send OTP failed.Please try again!");
				return "signup";
			}
				
			session.setAttribute("user", user);
			session.setAttribute("otp",otp);
			session.setAttribute("attempts", attempts);
			
			return "redirect:/BuySellConnect/enterotp"; 
		}
		
}  
		
