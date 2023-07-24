package com.BuySellConnect.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ProductController {
	
	@RequestMapping(path="/BuySellConnect/products",method = RequestMethod.GET)
	public String products() {
		
        return "products";
	}
}
