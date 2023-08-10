package com.BuySellConnect.web.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.BuySellConnect.web.dao.UserInfoRepository;
import com.BuySellConnect.web.dao.UserInfoRepositoryjpa;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.entities.UserProduct;

@Service
public class ProductService {
	
	// add new blog 
	@Autowired
	private UserInfoRepository userinforepo;
	
	@Autowired
	private UserInfoRepositoryjpa userinforepojpa;
	
	public void saveImage(String newimagename, MultipartFile file) throws IOException {
		
		File saveFile=new ClassPathResource("/static/productimages").getFile();
		Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newimagename);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void addNewProduct(UserInfo user,UserProduct product, MultipartFile file
			,String[] titleArray, String[] featureArray ) throws Exception {
		
		product.setUserInfo(user);
		Date date = new Date();
		product.setProductDate(date);
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String newimagename = user.getUsername() + "-" + formatDate.format(date) + ".jpg";
		saveImage(newimagename,file);
		product.setProductImage(newimagename);
		
		user.addProduct(product);
		this.userinforepo.save(user);
	}
		
	// get User info
	
	public UserInfo getUserInfo(String userName) {
		
		return userinforepojpa.getUserByUserName(userName);
		
	}
		
}
