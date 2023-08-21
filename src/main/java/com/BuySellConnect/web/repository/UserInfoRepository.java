package com.BuySellConnect.web.repository;

import com.BuySellConnect.web.entities.UserInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
	
	@Query("select u from UserInfo u where u.username = :username")
	public UserInfo getUserByUserName(@Param("username") String username);
	
	public List<UserInfo> findByMobileNumber(String mobileNumber);
	
	public List<UserInfo> findByEmail(String email);
	
	public boolean existsByUsernameAndMyinterestsInterestedProductId(String username, int interestedProductId);
	
}
