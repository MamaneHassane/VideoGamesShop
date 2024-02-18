package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.Admin;
import com.videogamesshop.vgs_package.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService){ this.adminService = adminService; }
    @PostMapping("/createOne")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Admin>> getAllAdmins(){
        List<Admin> admins = adminService.findAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") Long Id){
        Admin admin = adminService.findAdminById(Id);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<Admin> updateAdminById(@PathVariable("id") Long Id, @RequestBody Admin updatedAdmin){
        Admin admin = adminService.updateAdminById(updatedAdmin, Id);
        return new ResponseEntity<>(admin,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteOne/ {id}")
    public ResponseEntity<Admin> deleteAdminById(@PathVariable("id") Long Id){
        adminService.deleteAdminById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
