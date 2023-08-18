package com.BuySellConnect.web.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.repository.UserInfoRepository;

@Service
public class UserService {
	
	@Autowired
	private UserInfoRepository userinforepo;
	
	@Autowired
	private ProductService productservice;
	
	@Autowired
	private BCryptPasswordEncoder passswordEncoder;

	
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
	
	
	//generate random password
	public String generateRandomPassword() {
		
		int length = 10;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String randomString = "";
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString = randomString + characters.charAt(index);
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(randomString);
		return randomString;
	}
	
	// generate and save new password
	public void changePasswordRandom(String username) {
		
		UserInfo user = getUserInfo(username);
		user.setPassword(passswordEncoder.encode(generateRandomPassword()));
		userinforepo.save(user);
	}
	
	// change password
	public void changePassword(String username, String newPassword) {
		
		UserInfo user = getUserInfo(username);
		user.setPassword(passswordEncoder.encode(newPassword));
		userinforepo.save(user);
	}
	
	//create account
	public void createAccount(UserInfo user) {
		userinforepo.save(user);
	}
		
	//check is username already exists
	public Boolean checkUserName(String userName) {
		return userinforepo.existsById(userName);
	}
		
	//check if mobile number exists
	public Boolean checkMobileNumber(String mobileNumber) {
			
		List<UserInfo> temp = userinforepo.findByMobileNumber(mobileNumber);
			
		System.out.println(temp);
			
		if(temp.isEmpty())
			return false;
		return true;
	}
		
	// check if email exist
	public Boolean checkEmail(String email) {
			
		List<UserInfo> temp = userinforepo.findByEmail(email);
			
		System.out.println(temp);
			
		if(temp.isEmpty())
			return false;
		return true;
	}
		
	// get user details by email
	public UserInfo getInfobyEmail(String email) {
			
		List<UserInfo> temp = userinforepo.findByEmail(email);
		System.out.println(temp);
		if( temp.isEmpty() || temp.size()!=1 )
			return null;
		return temp.get(0);
	}

	public boolean checkPassword(String username, String newpassword) {
		UserInfo user = getUserInfo(username);
		return passswordEncoder.matches(newpassword, user.getPassword());
	}
		
}
