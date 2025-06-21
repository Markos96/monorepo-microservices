package com.microservices.order.controller;


import com.microservices.order.model.domain.Order;
import com.microservices.order.model.dto.OrderDTO;
import com.microservices.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok().body( this.orderService.saveOrder(orderDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.orderService.getOrderById(id));
    }
}
