package com.BuySellConnect.web.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.BuySellConnect.web.entities.ProductFeature;

public interface ProductFeatureRepository extends JpaRepository<ProductFeature,Integer>{

	 List<ProductFeature> findByProductId(int productId);
}
