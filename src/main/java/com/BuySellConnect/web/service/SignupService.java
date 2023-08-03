package com.BuySellConnect.web.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BuySellConnect.web.dao.UserInfoRepository;
import com.BuySellConnect.web.entities.UserInfo;

@Service
public class SignupService {
	
	@Autowired
	private UserInfoRepository userinforepo;
	
	// check whether mobile number is valid or not
	public boolean isValidMobileNo(String str)  
	{  
		//(0/91): number starts with (0/91)  
		//[7-9]: starting of the number may contain a digit between 0 to 9  
		//[0-9]: then contains digits 0 to 9  
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");  
		//the matcher() method creates a matcher that will match the given input against this pattern  
		Matcher match = ptrn.matcher(str);  
		//returns a boolean value  
		return (match.find() && match.group().equals(str));  
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
		
		List<UserInfo> temp = userinforepo.findBymobileNumber(mobileNumber);
		
		if(temp==null)
			return false;
		return true;
	}
	

}
