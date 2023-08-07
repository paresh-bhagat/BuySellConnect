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
	
	@RequestMapping(value="/myproducts",method = RequestMethod.GET)
	public String myProducts() {
		System.out.println("This is the my product page");
        return "myproducts";
	}
	
	@RequestMapping(value="/addproduct",method = RequestMethod.GET)
	public String addProduct() {
		System.out.println("This is the add product page");
        return "addproduct";
	}
	
	@RequestMapping(value="/editproduct",method = RequestMethod.GET)
	public String editProduct() {
		System.out.println("This is the edit product page");
        return "editproduct";
	}
	
}
