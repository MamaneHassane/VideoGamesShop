package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
     Optional<Customer> findByNameAndPassword(String userName, String password);
}
