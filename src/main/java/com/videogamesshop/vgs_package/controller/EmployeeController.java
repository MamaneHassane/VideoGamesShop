package com.videogamesshop.vgs_package.controller;

import com.videogamesshop.vgs_package.model.Employee;
import com.videogamesshop.vgs_package.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){ this.employeeService = employeeService; }
    @PostMapping("/createOne")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.findAllEmployee();
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long Id){
        Employee employee = employeeService.findEmployeeById(Id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") Long Id, @RequestBody Employee updatedEmployee){
        Employee employee = employeeService.updateEmployeeById(updatedEmployee,Id);
        return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") Long Id){
        employeeService.deleteEmployeeById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
