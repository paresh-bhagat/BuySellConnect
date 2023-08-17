package com.BuySellConnect.web.service;

import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.repository.UserInfoRepository;

@Service
public class UserService {
	
	@Autowired
	private UserInfoRepository userinforepo;
	
	@Autowired
	private ProductService productservice;
	
	// get User info
	public UserInfo getUserInfo(String userName) {
		return userinforepo.getUserByUserName(userName);
	}
	
	@Transactional
	public void deleteAccount(UserInfo user) throws IOException {
		
		// first delete all features and images
		for(int i=0; i<user.getProducts().size();i++) {
			this.productservice.deleteFeatures( Integer.toString(user.getProducts().get(i).getProductId()));
			this.productservice.deleteProductImage( Integer.toString(user.getProducts().get(i).getProductId()));
		}
		
		userinforepo.delete(user);
    }
}
