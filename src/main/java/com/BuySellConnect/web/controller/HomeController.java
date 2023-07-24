package com.BuySellConnect.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	// index or home page
	@RequestMapping(value="/BuySellConnect",method = RequestMethod.GET)
	public String home(Model model) {
		System.out.println("This is the home page");
		return "index";
	}
	
	// error page
	@RequestMapping(path="/BuySellConnect/error", method = RequestMethod.GET)
	public String errorPage() {
		System.out.print("error page");
		return "errorpage";
	}
	
}
