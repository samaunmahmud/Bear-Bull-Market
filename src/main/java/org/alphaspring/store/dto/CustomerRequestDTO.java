package org.alphaspring.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequestDTO {


    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message="Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;


    private String phone;


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
}
