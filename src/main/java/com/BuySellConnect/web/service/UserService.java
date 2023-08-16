package com.BuySellConnect.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.repository.UserInfoRepository;

@Service
public class UserService {
	
	@Autowired
	private UserInfoRepository userinforepo;
	
	// get User info
	public UserInfo getUserInfo(String userName) {
		return userinforepo.getUserByUserName(userName);
	}
		
	public void deleteAccount(UserInfo user) {
		userinforepo.delete(user);
    }
}
