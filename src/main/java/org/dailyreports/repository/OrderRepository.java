package org.dailyreports.repository;

import org.dailyreports.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByNumber(Long number);

}