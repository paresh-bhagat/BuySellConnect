package com.BuySellConnect.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.BuySellConnect.web.entities.UserInfo;

public class CustomUserInfoService implements UserDetailsService{
	
	@Autowired
	private UserInfoRepositoryjpa userinforepojpa;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo user = userinforepojpa.getUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		CustomUserInfo customeruserinfo = new CustomUserInfo(user);
		
		return customeruserinfo;
	}

}
