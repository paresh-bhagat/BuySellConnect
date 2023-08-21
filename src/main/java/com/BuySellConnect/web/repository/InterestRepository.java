package com.BuySellConnect.web.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.BuySellConnect.web.entities.Interest;


public interface InterestRepository extends JpaRepository<Interest, Integer>{
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Interest i WHERE i.productSeller = :productSeller")
    void deleteByProductSeller(String productSeller);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Interest i WHERE i.interestedProductId = :interestedProductId")
    void deleteByInterestedProductId(int interestedProductId);
	
	List<Interest> findByProductSeller(String productSeller);
	
	@Query("SELECT up FROM Interest up WHERE up.userInfo.username = :username ORDER BY up.interestId DESC")
    List<Interest> findLatestInterestByUsername(@Param("username") String username);
}
