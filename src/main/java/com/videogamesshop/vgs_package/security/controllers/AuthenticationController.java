package com.videogamesshop.vgs_package.security.controllers;

import com.videogamesshop.vgs_package.model.entities.Customer;
import com.videogamesshop.vgs_package.model.entities.Employee;
import com.videogamesshop.vgs_package.security.entities.AuthRequest;
import com.videogamesshop.vgs_package.security.entities.UserInfo;
import com.videogamesshop.vgs_package.security.services.JwtService;
import com.videogamesshop.vgs_package.security.services.UserInfoService;
import com.videogamesshop.vgs_package.service.CustomerService;
import com.videogamesshop.vgs_package.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    CustomerService customerService;
    EmployeeService employeeService;
    @Autowired
    public AuthenticationController(CustomerService customerService, EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
    }
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    @PostMapping("/customers/register")
    public String addNewCustomer(@RequestBody Customer userInfo) {
        if(service.alreadyExist(userInfo.getName())) return "Username already exists";
        userInfo.setRoles("USER"); // S'assurer qu'il n'ait pas d'autres rôles
        return customerService.addCustomer(userInfo);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN','MODERATOR'})")
    @PostMapping("/employees/register")
    public String  addNewEmployee(@RequestBody Employee userInfo) {
        if(service.alreadyExist(userInfo.getName())) return "Username already exists";
        userInfo.setRoles("USER,EMPLOYEE"); // S'assurer qu'il n'ait pas d'autres rôles
        return employeeService.addEmployee(userInfo);
    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/admins/register")
    public String addNewAdmin(@RequestBody UserInfo userInfo) {
        if(service.alreadyExist(userInfo.getName())) return "Username already exists";
        userInfo.setRoles("USER,EMPLOYEE,ADMIN"); // S'assurer qu'il n'ait pas d'autres rôles
        return service.addUser(userInfo);
    }
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
}
