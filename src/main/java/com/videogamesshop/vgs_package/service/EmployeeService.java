package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.EmployeeNotFoundException;
import com.videogamesshop.vgs_package.model.Employee;
import com.videogamesshop.vgs_package.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){ this.employeeRepository = employeeRepository; }
    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }
    public List<Employee> findAllEmployee(){
        return  employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long Id){
        return employeeRepository.findEmployeeById(Id)
                .orElseThrow(()->new EmployeeNotFoundException(Id));
    }

    public Employee updateEmployeeById(Employee updatedEmployee, Long Id){
        return employeeRepository.findById(Id).map(
                employee -> {
                    employee.setFirstName(updatedEmployee.getFirstName());
                    employee.setLastName(updatedEmployee.getLastName());
                    return employeeRepository.save(employee);
                }
        ).orElseThrow(()-> new EmployeeNotFoundException(Id));
    }

    public void deleteEmployeeById(Long Id){
        employeeRepository.deleteEmployeeById(Id);
    }

}
