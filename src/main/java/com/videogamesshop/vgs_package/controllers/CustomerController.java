package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.Customer;
import com.videogamesshop.vgs_package.model.records.AuthenticateCustomerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.videogamesshop.vgs_package.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
@PreAuthorize("hasAnyAuthority({'ADMIN,EMPLOYEE,MODERATOR'})")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customers= customerService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer newCustomer,@PathVariable("id") Long id) {
        Customer customer = customerService.updateCustomerById(newCustomer,id);
        return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
