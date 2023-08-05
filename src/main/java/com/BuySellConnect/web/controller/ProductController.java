package com.BuySellConnect.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/BuySellConnect/user") 
public class ProductController {
	
	@RequestMapping(value="/products",method = RequestMethod.GET)
	public String products() {
		System.out.println("This is the product page");
        return "products";
	}
	
}
