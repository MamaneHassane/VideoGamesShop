package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
