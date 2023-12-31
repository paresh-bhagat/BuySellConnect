package com.BuySellConnect.web.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.BuySellConnect.web.entities.UserInfo;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private UserInfo user;
	
	public UserDetailsImpl(UserInfo user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority simplegrantedauthority = new SimpleGrantedAuthority(user.getRole());
		
		return List.of(simplegrantedauthority);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
