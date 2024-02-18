package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {
    List<Rent> findAllByCustomer_IdOrderByEndDate(Long customerId);
    List<Rent> findAllByEmployee_Id(Long employeeId);
}
