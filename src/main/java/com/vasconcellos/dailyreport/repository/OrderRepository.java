package com.vasconcellos.dailyreport.repository;

import com.vasconcellos.dailyreport.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByNumber(Long number);

}