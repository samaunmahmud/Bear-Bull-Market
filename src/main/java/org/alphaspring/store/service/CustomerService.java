package org.alphaspring.store.service;

import org.alphaspring.store.dto.CustomerRequestDTO;
import org.alphaspring.store.dto.CustomerResponseDTO;
import org.alphaspring.store.entity.Customer;
import org.alphaspring.store.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Constructor Injection (Spring automatically plugs the repository in here!)
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // 1. Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 2. Get customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // 3. Save a new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // 4. Delete a customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // 5. Update an existing customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setEmail(updatedCustomer.getEmail());
                    existingCustomer.setPhone(updatedCustomer.getPhone());
                    return customerRepository.save(existingCustomer);
                })
                .orElse(null);
    }


    public Customer createCustomerFromDto(org.alphaspring.store.dto.CustomerRequestDTO dto) {
        Customer cutomer = new Customer();
        cutomer.setName(dto.getName());

        cutomer.setEmail(dto.getEmail());
        cutomer.setPhone(dto.getPhone());

        return customerRepository.save(cutomer);
    }


    // Helper method to map Entity -> Response DTO
    private CustomerResponseDTO mapToRespnseDTO(Customer customer) {
        return new CustomerResponseDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
    }

    public CustomerResponseDTO createCustomerFromDTO(CustomerRequestDTO dto){
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        Customer savedCustomer = customerRepository.save(customer);
        return mapToRespnseDTO(savedCustomer);

    }
}