package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
     Optional<Customer> findCustomerById(Long id);
    void deleteCustomerById(Long id);
}
