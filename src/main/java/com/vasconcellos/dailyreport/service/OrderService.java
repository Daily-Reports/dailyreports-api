package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.OrderDto;
import com.vasconcellos.dailyreport.mapper.OrderMapper;
import com.vasconcellos.dailyreport.model.Order;
import com.vasconcellos.dailyreport.model.OrderStatus;
import com.vasconcellos.dailyreport.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Order save(OrderDto data) {
        Order toSave = orderMapper.toEntity(data);
        toSave.setStatus(OrderStatus.NOT_STARTED);

        return orderRepository.save(toSave);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}