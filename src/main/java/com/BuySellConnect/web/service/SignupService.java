package com.BuySellConnect.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.repository.UserInfoRepository;

@Service
public class SignupService {
	
	@Autowired
	private UserInfoRepository userinforepo;
	
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
	
	

}
