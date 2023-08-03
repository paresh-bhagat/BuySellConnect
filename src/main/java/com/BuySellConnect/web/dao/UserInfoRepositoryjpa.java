package com.BuySellConnect.web.dao;

import com.BuySellConnect.web.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepositoryjpa extends JpaRepository<UserInfo,String> {
	
	@Query("select u from UserInfo u where u.username = :username")
	public UserInfo getUserByUserName(@Param("username") String username);

}
