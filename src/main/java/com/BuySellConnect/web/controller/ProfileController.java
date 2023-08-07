package com.BuySellConnect.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/BuySellConnect/user")
public class ProfileController {

	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String profile() {
		System.out.println("This is the profile page");
        return "profile";
	}
	
	@RequestMapping(value="/deleteaccount",method = RequestMethod.GET)
	public String deleteAccount() {
		System.out.println("This is the delete account page");
        return "redirect:/BuySellConnect";
	}
}
