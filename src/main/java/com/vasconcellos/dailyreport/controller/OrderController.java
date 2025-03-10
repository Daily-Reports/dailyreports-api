package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.order.OrderDto;
import com.vasconcellos.dailyreport.dto.order.OrderUpdateDto;
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
    public ResponseEntity<List<OrderDto>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto data) {
        return new ResponseEntity<>(orderService.save(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<OrderDto> update(@PathVariable long id, @RequestBody OrderUpdateDto data) {
        return ResponseEntity.ok(orderService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        orderService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}