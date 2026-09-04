package org.alphaspring.store.controller;


import org.alphaspring.store.entity.Order;
import org.alphaspring.store.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }



@GetMapping
    public List<Order> getAllOders(){
        return orderService.getAllOrders();
    }



    @PostMapping("/customer/{customerId}")
    public Order CreateOrder(@PathVariable Long customerId, @RequestBody Order order){
        return orderService.createOrder(customerId, order);
    }




    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable Long customerId){
        return orderService.getOrdersByCustomerId(customerId);
    }








}
