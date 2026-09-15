package org.alphaspring.store.controller;




import org.alphaspring.store.entity.Order;
import org.alphaspring.store.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1. Checkout the current user's cart
    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(Authentication authentication) {
        String username = authentication.getName();
        Order order = orderService.checkout(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    // 2. Get all orders for the authenticated user
    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(Authentication authentication) {
        String username = authentication.getName();
        List<Order> orders = orderService.getOrdersByUsername(username);
        return ResponseEntity.ok(orders);
    }
}
