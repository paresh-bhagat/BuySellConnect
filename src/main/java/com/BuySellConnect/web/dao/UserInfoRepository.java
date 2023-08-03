package com.BuySellConnect.web.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.BuySellConnect.web.entities.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo,String> {

	public List<UserInfo> findBymobileNumber(String mobileNumber);
}
