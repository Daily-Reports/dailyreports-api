package org.dailyreports.service;

import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.order.OrderDto;
import org.dailyreports.dto.order.OrderUpdateDto;
import org.dailyreports.exception.OrderNumberAlreadyUsedException;
import org.dailyreports.exception.ResourceNotFoundException;
import org.dailyreports.mapper.OrderMapper;
import org.dailyreports.model.*;
import org.dailyreports.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

;

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
        if(String.valueOf(data.getOrderNumber()).length() != 10)
            throw new OrderNumberAlreadyUsedException("The order number must be 10 digits long");

        if(orderRepository.findByOrderNumber(data.getOrderNumber()) != null)
            throw new OrderNumberAlreadyUsedException("This order number is already in use.");

        Event event = eventService.findByIdAsEntity(data.getEventId());
        Area area = areaService.findByIdAsEntity(data.getAreaId());
        Subarea subarea = null;

        if(data.getSubareaId() != null)
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