package com.BuySellConnect.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.BuySellConnect.web.entities.UserInfo;
import com.BuySellConnect.web.repository.UserInfoRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserInfoRepository userinforepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo user = userinforepo.getUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		UserDetailsImpl customeruserinfo = new UserDetailsImpl(user);
		
		return customeruserinfo;
	}

}
