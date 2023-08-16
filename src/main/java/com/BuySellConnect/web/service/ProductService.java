package com.BuySellConnect.web.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.BuySellConnect.web.dto.UserProductDto;
import com.BuySellConnect.web.entities.ProductFeature;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.entities.UserProduct;
import com.BuySellConnect.web.repository.ProductFeatureRepository;
import com.BuySellConnect.web.repository.UserInfoRepository;
import com.BuySellConnect.web.repository.UserProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private UserInfoRepository userinforepo;
	
	@Autowired
	private UserProductRepository productinforepo;

	@Autowired
	private ProductFeatureRepository productfeaturerepo;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public void saveImage(String newimagename, MultipartFile file) throws IOException {
		
		File saveFile=new ClassPathResource("/static/productimages").getFile();
		Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newimagename);
		System.out.println(path);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	}
	
	// add features for a product
	public void addProductFeatures(List<ProductFeature> featureList, int id) {
		
		for (int i = 0; i < featureList.size(); i++) {
			featureList.get(i).setProductId(id);
			this.productfeaturerepo.save(featureList.get(i));
        }
	}
	
	// add product
	public void addNewProduct(UserInfo user,UserProduct product,
			MultipartFile file, List<ProductFeature> featureList ) throws Exception {
		
		product.setUserInfo(user);
		Date date = new Date();
		product.setProductDate(date);
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String newimagename = user.getUsername() + "-" + formatDate.format(date) + ".jpg";
		saveImage(newimagename,file);
		product.setProductImage(newimagename);
		
		user.addProduct(product);
		this.userinforepo.save(user);
		
		List<UserProduct> userProducts = productinforepo.findLatestUserProductByUsername(product.getUserInfo().getUsername());
		int id = userProducts.get(0).getProductId();
		addProductFeatures(featureList,id);
	}
	
	// this is used for sorting product details to be shown to user
	
	public LinkedHashMap<String, List<List<String>>> sortMapUsingList(List<UserProduct> products) throws IOException{
			
		HashMap<String,List<List<String>>> map = new HashMap<String,List<List<String>>>();
		
		for (int i=0; i < products.size() ; i++) {
			map.put(products.get(i).getProductCategory(), new ArrayList<List<String>>() );
		}
		
		for (int i = 0; i < products.size(); i++) {
				
			List<String> temp = new ArrayList<String>();
				
			temp.add( Integer.toString(products.get(i).getProductId()));
			temp.add(products.get(i).getProductTitle());
			temp.add(Integer.toString(products.get(i).getProductPrice()));
			temp.add(products.get(i).getProductCity() + "," +products.get(i).getProductState());
			temp.add("/productimages/" + products.get(i).getProductImage());
			temp.add(products.get(i).getUserInfo().getUsername());
			
			map.get(products.get(i).getProductCategory()).add(temp);
	    }
			
		System.out.println(map);
			
		Comparator<List<List<String>>> bylistsize = 
		        	(List<List<String>> l1, List<List<String>> l2) -> Integer.compare(l1.size(),l2.size());
		        		
		LinkedHashMap<String, List<List<String>>> sortedMap = map.entrySet()
		    		.stream()
		    		.sorted(Map.Entry.<String, List<List<String>>>comparingByValue(bylistsize.reversed()))
		    		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println(sortedMap);

		return sortedMap;
			
	}
	
	// get products of a user
	public LinkedHashMap<String, List<List<String>>> getUserProducts(String userName) throws IOException {
			
		UserInfo temp = userinforepo.getUserByUserName(userName);
		return sortMapUsingList(temp.getProducts());
	}
	
	// get features of product
	public List<ProductFeature> getProductFeatures(String productId) {
		
		System.out.println("get product features");
		List<ProductFeature> temp = this.productfeaturerepo.findByProductId(Integer.parseInt(productId));
        return temp;
	}
	
	// get a product by id
	public UserProductDto getProductInfo(String productId) {
		
		System.out.println("get product info");
		UserProduct temp = this.productinforepo.findById(Integer.parseInt(productId)).get();
		UserProductDto productDto = this.modelMapper.map(temp, UserProductDto.class);
		String imagepath = "/productimages/" + productDto.getProductImage();
		productDto.setProductImage(imagepath);
        return productDto;
	}
	
	//get all products
	public LinkedHashMap<String, List<List<String>>> getAllProducts() throws IOException {
        return sortMapUsingList(this.productinforepo.findAll());
    }
	
	//delete all features of a product
	public void deleteFeatures(String productid) {
		this.productfeaturerepo.deleteByProductId(Integer.parseInt(productid));
	}
	
	//delete product image from host
	@Transactional
	private void deleteProductImage(String productid) throws IOException {
		
		UserProduct temp = this.productinforepo.findById(Integer.parseInt(productid)).get();
		UserProductDto productDto = this.modelMapper.map(temp, UserProductDto.class);
		File saveFile=new ClassPathResource("/static/productimages").getFile();
		Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + productDto.getProductImage());
		
		File imageFile = new File(path.toString());
		
		if (imageFile.exists()) {
            try {
                if (imageFile.delete()) {
                	System.out.println("image deleted");
                } else {
                    throw new IOException("Failed to delete image file");
                }
            } catch (IOException e) {
            	System.out.println("Caught io exception while deleeting image");
            }
        } 
	}
	
	//delete product
	@Transactional
	public void deleteProduct(UserInfo user, String productid) throws IOException {
		
		deleteFeatures(productid);
		deleteProductImage(productid);
		
		this.productinforepo.deleteById(Integer.parseInt(productid));
	}
	
}
