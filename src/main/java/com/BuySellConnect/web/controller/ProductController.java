package com.BuySellConnect.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.BuySellConnect.web.dto.UserProductDto;
import com.BuySellConnect.web.entities.ProductFeature;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.entities.UserProduct;
import com.BuySellConnect.web.service.ProductService;
import com.BuySellConnect.web.service.UserService;

@Controller
@RequestMapping("/BuySellConnect/user") 
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	@Autowired
	private UserService userservice;
	
	// product page
	@RequestMapping(value="/products",method = RequestMethod.GET)
	public String products(Principal principal,Model model) throws IOException {
		String name = principal.getName();
		UserInfo user = this.userservice.getUserInfo(name);
		LinkedHashMap<String, List<List<String>>> allproducts = this.productservice.getAllProducts();
		
		System.out.println(allproducts);
		model.addAttribute("products", allproducts);
		
		if(allproducts.isEmpty())
       	 model.addAttribute("noproducts", "No products found");
		
		System.out.println("This is the product page");
		System.out.println(user.getUsername());
		System.out.println(user.getMobileNumber());
		
        return "products";
	}
	
	// myproducts page
	@RequestMapping(value="/myproducts",method = RequestMethod.GET)
	public String myProducts(Principal principal,Model model) throws IOException {
		
		System.out.println("This is the my product page");
		String name = principal.getName();
		
		LinkedHashMap<String, List<List<String>>> userProducts = this.productservice.getUserProducts(name);
        
        model.addAttribute("products", userProducts);
        
        if(userProducts.isEmpty())
        	 model.addAttribute("noproducts", "No products found");
        
        return "myproducts";
	}
	
	// add product page
	@RequestMapping(value="/addproduct",method = RequestMethod.GET)
	public String addProduct(Model model) {
		System.out.println("This is the add product page");
		model.addAttribute("product", new UserProduct());
        return "addproduct";
	}

	// add product form handler
	@RequestMapping(value="/processaddproductform",method = RequestMethod.POST)
	public String addProductform(@Valid @ModelAttribute("product") UserProduct product,
	@RequestParam("featuretitle") String[] featuretitleArray,@RequestParam("featurecontent") String[] featurecontentArray,
	@RequestParam("productpic") MultipartFile file, Principal principal,
	BindingResult result,Model model) throws Exception {
		
		System.out.println(result.hasErrors());
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("product", product);
			return "addproduct";
		}
		
		String name = principal.getName();
		UserInfo user = this.userservice.getUserInfo(name);
		
		System.out.println("This is the add product page");
		System.out.println(product);
		
		for (int i = 0; i < featuretitleArray.length; i++) {
			Boolean errors=false;
			
            System.out.println(featuretitleArray[i]);
            
            if(featuretitleArray[i].length() < 1 || featuretitleArray[i].length() > 20) {
            	errors=true;
            	model.addAttribute("titleError", "Feature title between 1 to 20 characters");
            }
            
            System.out.println(featurecontentArray[i]);
            if(featurecontentArray[i].length() < 1 || featurecontentArray[i].length() > 70) {
            	errors=true;
            	model.addAttribute("featureError", "Feature content between 1 to 70 characters");
            }
            
            if(errors) {
            	model.addAttribute("product", product);
            	System.out.println(product.getProductDescription());
        		System.out.println(product.getProductOverview());
    			return "addproduct";
            }
        }

		//process file
		if( file==null || file.isEmpty()==true ){
			model.addAttribute("ImageError","Please upload an image");
			model.addAttribute("product", product);
			System.out.println(product.getProductDescription());
			System.out.println(product.getProductOverview());
			return "addproduct";
		}

		if(file.getSize() > 5242880)
		{
			model.addAttribute("ImageError","Image size is greater than 5MB");
			model.addAttribute("product", product);
			System.out.println(product.getProductDescription());
			System.out.println(product.getProductOverview());
			return "addproduct";
		}
			
		System.out.println(file.getContentType());
		if( !(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) )
		{
			model.addAttribute("ImageError","Uploaded file not in jpeg/png format");
			model.addAttribute("product", product);
			
			System.out.println(product.getProductDescription());
			System.out.println(product.getProductOverview());
			return "addproduct";
		}
		
		List<ProductFeature> featureList = new ArrayList<>();
		
		for (int i = 0; i < featuretitleArray.length; i++) {
			ProductFeature feature = new ProductFeature();
			feature.setFeatureTitle(featuretitleArray[i]);
			feature.setFeatureContent(featurecontentArray[i]);
			featureList.add(feature);
        }
		
		this.productservice.addNewProduct(user,product,file,featureList);
		model.addAttribute("addProductSuccess","Product Successfully added");
		model.addAttribute("product", product);
        return "addproduct";
	}
	
	// edit product
	@RequestMapping(value="/updateproduct",method = RequestMethod.GET)
	public String updateProduct(@RequestParam String username,
	@RequestParam String productId, Principal principal, Model model) {
		System.out.println("This is the update product page");
		UserProductDto product = this.productservice.getProductInfo(productId);
		System.out.println(product);
		List<ProductFeature> features = this.productservice.getProductFeatures(productId);
		System.out.println(features);
		
		model.addAttribute("product", product);
		model.addAttribute("features", features);
        return "updateproduct";
	}
	
	// edit product form
	@RequestMapping(value="/processupdateproductform",method = RequestMethod.POST)
	public String updateProductForm(@Valid @ModelAttribute("product") UserProduct product,
			@RequestParam(value="featuretitle",required = false) String[] featuretitleArray,
			@RequestParam(value="featurecontent",required = false) String[] featurecontentArray,
			@RequestParam("productpic") MultipartFile file,
			@RequestParam String productId, Principal principal,
			BindingResult result,Model model) throws Exception {
		System.out.println("This is the update product form page");
		System.out.println(result.hasErrors());
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("product", product);
			return "updateproduct";
		}
		UserProductDto productold = this.productservice.getProductInfo(productId);
		String name = principal.getName();
		UserInfo user = this.userservice.getUserInfo(name);
		System.out.println(product);
		
		List<ProductFeature> featureList = new ArrayList<>();
		
		if(featuretitleArray!=null) {
			
			for (int i = 0; i < featuretitleArray.length; i++) {
				Boolean errors=false;
				
	            System.out.println("helllooooooooooooooooooooooooooooooooooooooo");
	            
	            if(featuretitleArray[i].length() < 1 || featuretitleArray[i].length() > 20) {
	            	errors=true;
	            	model.addAttribute("titleError", "Feature title between 1 to 20 characters");
	            }
	            
	            System.out.println(featurecontentArray[i]);
	            if(featurecontentArray[i].length() < 1 || featurecontentArray[i].length() > 70) {
	            	errors=true;
	            	model.addAttribute("featureError", "Feature content between 1 to 70 characters");
	            }
	            
	            if(errors) {
	            	model.addAttribute("product", product);
	            	System.out.println(product.getProductDescription());
	        		System.out.println(product.getProductOverview());
	    			return "updateproduct";
	            }
	        }
			
			for (int i = 0; i < featuretitleArray.length; i++) {
				ProductFeature feature = new ProductFeature();
				feature.setFeatureTitle(featuretitleArray[i]);
				feature.setFeatureContent(featurecontentArray[i]);
				featureList.add(feature);
	        }

		}
		
		//process file
		if(!(file==null || file.isEmpty()==true )){
			if(file.getSize() > 5242880)
			{
				model.addAttribute("ImageError","Image size is greater than 5MB");
				model.addAttribute("product", product);
				System.out.println(product.getProductDescription());
				System.out.println(product.getProductOverview());
				return "updateproduct";
			}
				
			System.out.println(file.getContentType());
			if( !(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) )
			{
				model.addAttribute("ImageError","Uploaded file not in jpeg/png format");
				model.addAttribute("product", product);
				
				System.out.println(product.getProductDescription());
				System.out.println(product.getProductOverview());
				return "updateproduct";
			}
		}
		
		product.setProductId(Integer.parseInt(productId));
		product.setProductImage(productold.getProductImage().replace("/productimages/", ""));
		this.productservice.updateProduct(user, product, file, featureList);
		model.addAttribute("updateProductSuccess","Product Successfully updated");
		model.addAttribute("product", product);
     
	    return "updateproduct";
	}
	
	// view product
	@RequestMapping(value="/viewproduct",method = RequestMethod.GET)
	public String viewProduct(@RequestParam String username,
			@RequestParam String productId, Model model,Principal principal) {
		String name = principal.getName();
		System.out.println("This is the view product page");
		model.addAttribute("username", username);
		
		UserProductDto product = this.productservice.getProductInfo(productId);
		System.out.println(product);
		
		List<ProductFeature> features = this.productservice.getProductFeatures(productId);
		
		if(features==null || features.isEmpty())
			model.addAttribute("nofeatures","No features added for this product.");
			
		System.out.println(features);
		
		
		if(username.equals(name))
			model.addAttribute("seller","s");
		else {
			if(this.userservice.checkProductInterest(name, productId))
				model.addAttribute("requestadded","Product Request already placed");
			else
				model.addAttribute("buyer","b");
		}
			
		model.addAttribute("product", product);
		model.addAttribute("features", features);
        return "viewproduct";
	}
	
	// delete product
	@RequestMapping(value="/deleteproduct",method = RequestMethod.GET)
	public String deleteProduct(@RequestParam String username,
			@RequestParam String productId, Model model) throws IOException {
		System.out.println("This is the delete product page");
		this.productservice.deleteProduct(productId);
		return "redirect:/BuySellConnect/user/products";
	}
}
