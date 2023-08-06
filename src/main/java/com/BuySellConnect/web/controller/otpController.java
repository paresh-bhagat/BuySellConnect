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
import com.BuySellConnect.web.service.SignupService;

@Controller
@RequestMapping("/BuySellConnect")
public class otpController {
	
	@Autowired
	private SignupService signupservice;
	
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
	public String handleOtpForm( @RequestParam("first") String first, @RequestParam("second") String second, 
			@RequestParam("third") String third, @RequestParam("fourth") String fourth, 
			HttpServletRequest request, HttpSession session, Model model) {
		
		int num1 = Integer.parseInt(first);
		int num2 = Integer.parseInt(second);
		int num3 = Integer.parseInt(third);
		int num4 = Integer.parseInt(fourth);
		
		session = request.getSession(true);
		int[] otp = (int[])session.getAttribute("otp");
		
		if ( num1==otp[0] && num2==otp[1] && num3==otp[2] && num4==otp[3] ) {
			
			UserInfo user = (UserInfo)session.getAttribute("user");
			System.out.println(user);
			session.removeAttribute("user");
			session.removeAttribute("otp");
			session.removeAttribute("attempts");
			signupservice.createAccount(user);
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
}
