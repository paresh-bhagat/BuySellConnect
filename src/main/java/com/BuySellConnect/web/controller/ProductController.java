package com.BuySellConnect.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BuySellConnect.web.entities.ProductFeature;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.entities.UserProduct;
import com.BuySellConnect.web.service.ProductService;

@Controller
@RequestMapping("/BuySellConnect/user") 
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	@RequestMapping(value="/products",method = RequestMethod.GET)
	public String products(Principal principal) {
		String name = principal.getName();
		UserInfo user = this.productservice.getUserInfo(name);
		
		System.out.println("This is the product page");
		System.out.println(user.getUsername());
		System.out.println(user.getMobileNumber());
		
        return "products";
	}
	
	@RequestMapping(value="/myproducts",method = RequestMethod.GET)
	public String myProducts() {
		System.out.println("This is the my product page");
        return "myproducts";
	}
	
	@RequestMapping(value="/addproduct",method = RequestMethod.GET)
	public String addProduct(Model model) {
		System.out.println("This is the add product page");
		model.addAttribute("product", new UserProduct());
        return "addproduct";
	}

	// add product form handler
	@RequestMapping(value="/processaddproductform",method = RequestMethod.POST)
	public String addProductform(@Valid @ModelAttribute("product") UserProduct product,
	@RequestParam("title") String[] titleArray,@RequestParam("feature") String[] featureArray,
	@RequestParam("productpic") MultipartFile file, Principal principal,
	BindingResult result,Model model) throws Exception {
		
		String name = principal.getName();
		UserInfo user = this.productservice.getUserInfo(name);
		
		System.out.println("This is the add product page");
		System.out.println(product.getProductCategory());
		System.out.println(product.getProductCity());
		System.out.println(product.getProductDescription());
		System.out.println(product.getProductOverview());
		System.out.println(product.getProductTitle());
		System.out.println(product.getProductState());
		System.out.println(product.getProductCity());
		
		for (int i = 0; i < titleArray.length; i++) {
            System.out.println(titleArray[i]);
            System.out.println(featureArray[i]);
        }

		//process file
		if( file==null || file.isEmpty()==true ){
			model.addAttribute("emptyfile","Please upload an image");
			return "addproduct";
		}

		if(file.getSize() > 5242880)
		{
			model.addAttribute("largefile","Image size is greater than 5MB");
			return "addproduct";
		}
			
		
		if( !(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) )
		{
			model.addAttribute("wrongfile","Uploaded file not in jpeg/png format");
			return "addproduct";
		}
		
		List<ProductFeature> featureList = new ArrayList<>();
		
		for (int i = 0; i < titleArray.length; i++) {
			ProductFeature feature = new ProductFeature();
			feature.setFeatureTitle(titleArray[i]);
			feature.setFeatureContent(featureArray[i]);
			feature.setUserProduct(product);
			featureList.add(feature);
        }
		
		product.setProductFeatures(featureList);
		this.productservice.addNewProduct(user,product,file,titleArray,featureArray);
		
		model.addAttribute("addProductSuccess","Product Successfully added");
        return "addproduct";
	}
	
	@RequestMapping(value="/editproduct",method = RequestMethod.GET)
	public String editProduct() {
		System.out.println("This is the edit product page");
        return "editproduct";
	}
	
}
