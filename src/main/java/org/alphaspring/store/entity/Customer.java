package org.alphaspring.store.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message ="Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    private String phone;


    public Customer(){}


    public Customer(String name, String email,String phone){
        this.name= name;
        this.email = email;
        this.phone= phone;

    }


    public Long getId(){return id;}

    public void setId(Long id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone=phone;}



    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cutomer")
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();


    public List<Order> getOrders(){
        return orders;
    }
    public void setOrders(List<Order> orders){
        this.orders=orders;
    }




}
