package com.BuySellConnect.web.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.BuySellConnect.web.dto.UserProductDto;
import com.BuySellConnect.web.entities.ProductFeature;
import com.BuySellConnect.web.service.InterestsRequestsService;
import com.BuySellConnect.web.service.ProductService;

@Controller
@RequestMapping("/BuySellConnect/user")
public class InterestsRequestsController {
	
	@Autowired
	private ProductService productservice;
	
	@Autowired
	private InterestsRequestsService interestsrequestsservice;
	
	@RequestMapping(value="/myinterests",method = RequestMethod.GET)
	public String interests(Principal principal, Model model) {
		System.out.println("This is the interested product page");
		String name = principal.getName();
		List<List<String>> features = this.interestsrequestsservice.getInterestProduct(name);
		
		if(features.isEmpty())
			model.addAttribute("nointerests", "No records found");
		else
			model.addAttribute("features", features);
		
        return "myinterests";
	}

	@RequestMapping(value="/addinterest",method = RequestMethod.GET)
	public String addInterestProduct(@RequestParam String username,
			@RequestParam String productId, Principal principal, Model model) {
		
		System.out.println("This is the add interested product page");
		String name = principal.getName();
		model.addAttribute("username", username);
		
		int interestId = this.interestsrequestsservice.addInterestProduct(name, username,productId);
		
		UserProductDto product = this.productservice.getProductInfo(productId);
		System.out.println(product);
		
		List<ProductFeature> features = this.productservice.getProductFeatures(productId);
		System.out.println(features);
		
		model.addAttribute("requestadded","Product Request placed");
		
		this.interestsrequestsservice.sendEmailNewInterestBuyerSeller(name,username,productId,interestId);
		
		model.addAttribute("product", product);
		model.addAttribute("features", features);
		model.addAttribute("interestadded", "Seller has been notified and contant info sent to your email");
		
		return "viewproduct";
	}
	
	@RequestMapping(value="/deleteinterest",method = RequestMethod.GET)
	public String deleteInterests(@RequestParam String username,
			@RequestParam String interestId, Principal principal, Model model) {
		System.out.println("This is the delete interest page");
		
		String name = principal.getName();
		
		//send mail
		this.interestsrequestsservice.sendEmailCancelInterestBuyerSeller(name,username,interestId);
		//delete interest
		this.interestsrequestsservice.deleteInterestProduct(interestId);
		
		
		List<List<String>> features = this.interestsrequestsservice.getInterestProduct(name);
		
		if(features.isEmpty())
			model.addAttribute("nointerests", "No records found");
		else
			model.addAttribute("features", features);
		
		
		
		model.addAttribute("interestdeleted", "Product Request Cancelled");
		
		return "myinterests";
	}
	
	//////////////////////////////////////////////////////
	
	@RequestMapping(value="/productrequests",method = RequestMethod.GET)
	public String productRequests(Principal principal, Model model) {
		System.out.println("This is the delete requests page");
		String name = principal.getName();
		
		
		List<List<String>> productRequests = this.interestsrequestsservice.getProductRequests(name);
		
		if(productRequests.isEmpty())
			model.addAttribute("norequests", "No records found");
		else {
			model.addAttribute("requests", productRequests);
			model.addAttribute("username", name);
		}
		
        return "productrequests";
	}
	
	@RequestMapping(value="/deleterequest",method = RequestMethod.GET)
	public String deleteRequests(@RequestParam String username,
			@RequestParam String interestId, Principal principal, Model model) {
		System.out.println("This is the delete product requests page");
		
		String name = principal.getName();
		this.interestsrequestsservice.sendEmailCancelRequestBuyerSeller(username, name, interestId);
		
		//delete request
		this.interestsrequestsservice.deleteInterestProduct(interestId);
				
		
		List<List<String>> productRequests = this.interestsrequestsservice.getProductRequests(name);
		
		if(productRequests.isEmpty())
			model.addAttribute("norequests", "No records found");
		else
			model.addAttribute("requests", productRequests);
		
		
		
		model.addAttribute("requestreject", "Product Request Rejected");
		
        return "productrequests";
	}

}
