package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.CustomerNotFoundException;
import com.videogamesshop.vgs_package.model.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.videogamesshop.vgs_package.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private PasswordEncoder encoder;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder encoder) {
        this.customerRepository = customerRepository;
        this.encoder = encoder;
    }
    public String addCustomer(Customer customer){
        customer.setPassword(encoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        return "Customer added successfully" ;
    }
    public Customer increaseBalance(Long customerId, double amountToAdd){
        return customerRepository.findById(customerId).map(
                customer -> {
                    customer.setMoneyBalance(customer.getMoneyBalance()+amountToAdd);
                    return customerRepository.save(customer);
                }).orElseThrow(()->new CustomerNotFoundException(customerId));
    }
    public Customer decreaseBalance(Long customerId, double amountToRemove){
        return customerRepository.findById(customerId).map(
                customer -> {
                    customer.setMoneyBalance(customer.getMoneyBalance()-amountToRemove);
                    return customerRepository.save(customer);
                }).orElseThrow(()->new CustomerNotFoundException(customerId));
    }
    public List<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }
    public Customer findByUserNameAndPassword(String userName, String password) {
        return customerRepository.findByNameAndPassword(userName,password).orElseThrow(()->
            new CustomerNotFoundException(userName,password)
        );
    }
    public Customer updateCustomerById(Customer updatedCustomer, Long id) {
        return customerRepository.findById(id).map(
                (customer) -> {
                    customer.setFirstName(updatedCustomer.getFirstName());
                    customer.setLastName(updatedCustomer.getLastName());
                    customer.setName(updatedCustomer.getName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setPassword(updatedCustomer.getPassword());
                    customer.setBullets(updatedCustomer.getBullets());
                    return  customerRepository.save(customer);
                }).orElseThrow(()-> new CustomerNotFoundException(id));
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(id));
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

}
