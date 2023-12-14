package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.CustomerNotFoundException;
import com.videogamesshop.vgs_package.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.videogamesshop.vgs_package.repository.CustomerRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public void addCustomer(Customer customer) {
        customer.setCustomerCode(UUID.randomUUID().toString());
        customerRepository.save(customer);
    }

    public List<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer updateCustomerById(Customer updatedCustomer, Long id) {
        return customerRepository.findById(id).map(
                (customer) -> {
                    customer.setCustomerFirstName(updatedCustomer.getCustomerFirstName());
                    customer.setCustomerLastName(updatedCustomer.getCustomerLastName());
                    customer.setCustomerDateOfBirth(updatedCustomer.getCustomerDateOfBirth());
                    return  customerRepository.save(customer);
                }).orElseThrow(()-> new CustomerNotFoundException(id));
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findCustomerById(id)
                .orElseThrow(()-> new CustomerNotFoundException(id));
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteCustomerById(id);
    }

}
