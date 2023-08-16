package com.BuySellConnect.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BuySellConnect.web.entities.UserProduct;

public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {
	
	@Query("SELECT up FROM UserProduct up WHERE up.userInfo.username = :username ORDER BY up.productId DESC")
    List<UserProduct> findLatestUserProductByUsername(@Param("username") String username);
}
