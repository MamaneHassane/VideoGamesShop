package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.Customer;
import com.videogamesshop.vgs_package.model.entities.Employee;
import com.videogamesshop.vgs_package.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){ this.employeeService = employeeService; }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @PostMapping("/register")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.findAllEmployee();
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long Id){
        Employee employee = employeeService.findEmployeeById(Id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") Long Id, @RequestBody Employee updatedEmployee){
        Employee employee = employeeService.updateEmployeeById(updatedEmployee,Id);
        return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") Long Id){
        employeeService.deleteEmployeeById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
