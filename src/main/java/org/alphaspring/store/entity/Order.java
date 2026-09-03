package org.alphaspring.store.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="orders")
public class Order {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate orderDate;
    private Double totalAmount;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    public Order() {}

    public Order(LocalDate orderDate, Double totalAmount, Customer customer) {
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.customer = customer;

    }


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;

    }
    public LocalDate getOrderDate(){
        return orderDate;

    }
    public void setOrderDate(LocalDate orderDate){
        this.orderDate = orderDate;
    }
    public Double getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount){
        this.totalAmount = totalAmount;
    }


    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



}
