package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.OrderDto;
import com.vasconcellos.dailyreport.dto.OrderUpdateDescriptionDto;
import com.vasconcellos.dailyreport.dto.OrderUpdateStatusDto;
import com.vasconcellos.dailyreport.model.Order;
import com.vasconcellos.dailyreport.model.OrderStatus;
import com.vasconcellos.dailyreport.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Order> create(@Valid @RequestBody OrderDto data) {
        return new ResponseEntity<>(orderService.save(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update-status")
    public ResponseEntity<Order> updateStatus(@PathVariable long id, @RequestBody OrderUpdateStatusDto data) {
        Optional<Order> orderOptional = orderService.findById(id);

        if(orderOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Order order = orderOptional.get();

        order.setEndDate(data.getStatus() != OrderStatus.DONE ? null : data.getEndDate());
        order.setStatus(data.getStatus());

        orderService.save(order);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}/update-description")
    public ResponseEntity<Order> updateDescription(@PathVariable long id, @RequestBody OrderUpdateDescriptionDto data) {
        Optional<Order> orderOptional = orderService.findById(id);

        if(orderOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Order order = orderOptional.get();

        order.setDescription(data.getDescription());
        orderService.save(order);

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        orderService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
