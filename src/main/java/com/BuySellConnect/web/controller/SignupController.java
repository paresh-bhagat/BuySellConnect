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
import com.BuySellConnect.web.service.UserService;
import com.BuySellConnect.web.service.emailService;
import com.BuySellConnect.web.service.otpService;

@Controller
@RequestMapping("/BuySellConnect")
public class SignupController {
	
		@Autowired
		private otpService otpservice;
		
		@Autowired
		private UserService userservice;
		
		@Autowired
		private BCryptPasswordEncoder passswordEncoder;
		
		@Autowired
	    private emailService emailservice;
	
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
			
			if(userservice.checkUserName(user.getUsername())) {
				System.out.println("Username already exist");
				model.addAttribute("errorMessage", "Username already exist!");
				model.addAttribute("userInfo", user);
				return "signup";
			}
				
			if(userservice.checkMobileNumber(user.getMobileNumber())) {
				System.out.println("phonenumber already exist");
				model.addAttribute("errorMessage", "Mobile number already exist!");
				model.addAttribute("userInfo", user);
				return "signup";
			}
			
			if(userservice.checkEmail(user.getEmail())) {
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
			int[] otpPhone = otpservice.getOtp();
			int[] otpEmail = otpservice.getOtp();
			
			
			Boolean sendOtpStatusEmail =  true; //emailservice.sendOTPEmail(user.getUsername(), user.getEmail(),otpEmail);
			
			Boolean sendOtpStatusPhone =  true; //otpservice.sendOtpSms(user.getMobileNumber(), otpPhone);
			
			
			otpPhone[0]=0; otpPhone[1]=0;otpPhone[2]=0;otpPhone[3]=0;
			otpEmail[0]=1; otpEmail[1]=2;otpEmail[2]=3;otpEmail[3]=4;
			
			if(sendOtpStatusPhone==false || sendOtpStatusEmail==false) {
				System.out.println("Otp not send");
				model.addAttribute("errorMessage", "Send OTP failed.Please try again!");
				return "signup";
			}
				
			session.setAttribute("user", user);
			session.setAttribute("otpphone",otpPhone);
			session.setAttribute("otpemail",otpEmail);
			session.setAttribute("attempts", attempts);
			
			return "redirect:/BuySellConnect/enterotp"; 
		}
		
}  
		
