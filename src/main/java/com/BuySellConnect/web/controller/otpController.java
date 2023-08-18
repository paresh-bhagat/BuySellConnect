package com.BuySellConnect.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.service.UserService;

@Controller
@RequestMapping("/BuySellConnect")
public class otpController {
	
	@Autowired
	private UserService userservice;
	
	// otp page
	@RequestMapping(value="/enterotp",method = RequestMethod.GET)
	public String otpHandler(HttpServletRequest request, 
			HttpSession session, Model model) {
		
		session = request.getSession(true);
		int attempts =  (int)session.getAttribute("attempts");
		UserInfo user = (UserInfo)session.getAttribute("user");
		String mobile_number = user.getMobileNumber();
		String last4digits = mobile_number.substring(mobile_number.length() - 4);
		System.out.print(last4digits);
		
		model.addAttribute("last4digits",last4digits);
		model.addAttribute("attempts", "Attempts remaining : " + Integer. toString(attempts));
		
		if(attempts<3) {
			model.addAttribute("wrongotp","Wrong OTP entered");
		}
		
		if(attempts==0) {
			model.addAttribute("user", new UserInfo());
			model.addAttribute("attempts_exceed","Maximum attempt exceeded");
			return "signup";
		}
			
		System.out.println("This is the otp page");
		return "otp";
	}
			
	// otp form
	@RequestMapping(path="/processotpform", method=RequestMethod.POST)
	public String handleOtpForm( @RequestParam("first") String first, 
			@RequestParam("second") String second, 
			@RequestParam("third") String third, 
			@RequestParam("fourth") String fourth,
			@RequestParam("efirst") String efirst,
			@RequestParam("esecond") String esecond, 
			@RequestParam("ethird") String ethird, 
			@RequestParam("efourth") String efourth,
			HttpServletRequest request, HttpSession session, Model model) {
		
		int nump1 = Integer.parseInt(first);
		int nump2 = Integer.parseInt(second);
		int nump3 = Integer.parseInt(third);
		int nump4 = Integer.parseInt(fourth);
		
		int nume1 = Integer.parseInt(efirst);
		int nume2 = Integer.parseInt(esecond);
		int nume3 = Integer.parseInt(ethird);
		int nume4 = Integer.parseInt(efourth);
		
		session = request.getSession(true);
		int[] otpPhone = (int[])session.getAttribute("otpphone");
		int[] otpEmail = (int[])session.getAttribute("otpemail");
		
		if ( nump1==otpPhone[0] && nump2==otpPhone[1] && nump3==otpPhone[2] && nump4==otpPhone[3] &&
				nume1==otpEmail[0] && nume2==otpEmail[1] && nume3==otpEmail[2] && nume4==otpEmail[3] ) {
			
			UserInfo user = (UserInfo)session.getAttribute("user");
			System.out.println(user);
			session.removeAttribute("user");
			session.removeAttribute("otpphone");
			session.removeAttribute("otpemail");
			session.removeAttribute("attempts");
			userservice.createAccount(user);
		}
		else {
			
			int attempts =  (int)session.getAttribute("attempts");
			attempts--;
			session.setAttribute("attempts", attempts);
			return "redirect:/BuySellConnect/enterotp";
		}
		
		model.addAttribute("SignUpMessage", "Signup Successfull please login!");
		model.addAttribute("user", new UserInfo());
		return "login";
	}
	
	// otp page
	@RequestMapping(value="/emailotp",method = RequestMethod.GET)
	public String forgotPasswordotpHandler(HttpServletRequest request, 
			HttpSession session, Model model) {
			
			session = request.getSession(true);
			int attempts =  (int)session.getAttribute("attempts");
			
			model.addAttribute("attempts", "Attempts remaining : " + Integer. toString(attempts));
			
			if(attempts<3) {
				model.addAttribute("wrongotp","Wrong OTP entered");
			}
			
			if(attempts==0) {
				model.addAttribute("user", new UserInfo());
				model.addAttribute("attempts_exceed","Maximum attempt exceeded");
				return "forgotpassword";
			}
				
			System.out.println("This is the email otp page");
			return "emailotp";
		}
				
		// otp form
		@RequestMapping(path="/processemailotpform", method=RequestMethod.POST)
		public String handleForgotPasswordOtpForm( @RequestParam("first") String first, 
				@RequestParam("second") String second, 
				@RequestParam("third") String third, 
				@RequestParam("fourth") String fourth,
				HttpServletRequest request, HttpSession session, Model model) {
			
			int num1 = Integer.parseInt(first);
			int num2 = Integer.parseInt(second);
			int num3 = Integer.parseInt(third);
			int num4 = Integer.parseInt(fourth);
			
			session = request.getSession(true);
			int[] otpEmail = (int[])session.getAttribute("otpemail");
			
			if ( num1==otpEmail[0] && num2==otpEmail[1] && num3==otpEmail[2] && num4==otpEmail[3] ) {
				
				String user = (String) session.getAttribute("user");
				System.out.println(user);
				session.removeAttribute("user");
				session.removeAttribute("otpemail");
				session.removeAttribute("attempts");
				userservice.changePasswordRandom(user);
			}
			else {
				
				int attempts =  (int)session.getAttribute("attempts");
				attempts--;
				session.setAttribute("attempts", attempts);
				return "redirect:/BuySellConnect/emailotp";
			}
			
			model.addAttribute("changepasswordsuccessful", "New password sent to Email");
			model.addAttribute("user", new UserInfo());
			return "login";
		}
	
}
