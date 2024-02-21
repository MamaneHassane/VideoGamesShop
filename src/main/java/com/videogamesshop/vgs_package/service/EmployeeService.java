package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.EmployeeNotFoundException;
import com.videogamesshop.vgs_package.model.entities.Employee;
import com.videogamesshop.vgs_package.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder encoder){
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
    }
    public String addEmployee(Employee employee){
        employee.setPassword(encoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return "Employee added successfully";
    }
    public List<Employee> findAllEmployee(){
        return  employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long Id){
        return employeeRepository.findById(Id)
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
        employeeRepository.deleteById(Id);
    }

}
