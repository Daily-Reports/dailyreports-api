package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.OrderDto;
import com.vasconcellos.dailyreport.model.Order;
import com.vasconcellos.dailyreport.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        orderService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
