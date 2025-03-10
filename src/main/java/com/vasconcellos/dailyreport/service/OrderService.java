package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.order.OrderDto;
import com.vasconcellos.dailyreport.dto.order.OrderUpdateDto;
import com.vasconcellos.dailyreport.exception.OrderNumberAlreadyUsedException;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.mapper.OrderMapper;
import com.vasconcellos.dailyreport.model.*;
import com.vasconcellos.dailyreport.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final EventService eventService;
    private final AreaService areaService;
    private final SubareaService subareaService;

    public OrderDto save(OrderDto data) {
        if(orderRepository.findByNumber(data.getNumber()) != null)
            throw new OrderNumberAlreadyUsedException("This order number is already in use.");

        Event event = eventService.findByIdAsEntity(data.getEventId());
        Area area = areaService.findByIdAsEntity(data.getAreaId());
        Subarea subarea = null;

        if(data.getAreaId() != null)
            subarea = subareaService.findByIdAsEntity(data.getSubareaId());

        Order order = orderMapper.toEntity(data, event, area, subarea);
        order.setStatus(OrderStatus.NOT_STARTED);

        return orderMapper.toDto(orderRepository.save(order));
    }

    public OrderDto update(Long id, OrderUpdateDto data) {
        Order order = findByIdAsEntity(id);

        if(data.getDescription() != null)
            order.setDescription(data.getDescription());

        if(data.getStatus() != null) {
            if(data.getEndDate() != null)
                order.setEndDate(data.getStatus() != OrderStatus.DONE ? null : data.getEndDate());

            order.setStatus(data.getStatus());
        }

        return orderMapper.toDto(orderRepository.save(order));
    }

    public OrderDto findById(Long id) {
        return orderMapper.toDto(findByIdAsEntity(id));
    }

    public Order findByIdAsEntity(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order cannot be found"));
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}