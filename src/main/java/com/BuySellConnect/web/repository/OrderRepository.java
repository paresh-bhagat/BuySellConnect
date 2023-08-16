package com.BuySellConnect.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BuySellConnect.web.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
