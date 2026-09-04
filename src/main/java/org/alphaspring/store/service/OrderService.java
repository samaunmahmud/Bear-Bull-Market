package org.alphaspring.store.service;


import org.alphaspring.store.entity.Customer;
import org.alphaspring.store.entity.Order;
import org.alphaspring.store.repository.CustomerRepository;
import org.alphaspring.store.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;


    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }


    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order createOrder(Long customerId, Order order){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        order.setCustomer(customer);

        return orderRepository.save(order);
    }

}
