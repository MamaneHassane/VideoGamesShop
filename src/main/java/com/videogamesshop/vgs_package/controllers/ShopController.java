package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.Employee;
import com.videogamesshop.vgs_package.model.entities.RoleInCareer;
import com.videogamesshop.vgs_package.model.entities.Shop;
import com.videogamesshop.vgs_package.model.records.AddEmployeeToShopRecord;
import com.videogamesshop.vgs_package.model.records.MutateEmployeeRecord;
import com.videogamesshop.vgs_package.service.ShopService;
import jakarta.el.MethodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
@PreAuthorize("hasAnyAuthority({'MODERATOR'})")
public class ShopController {
    private final ShopService shopService;
    public ShopController(ShopService shopService) { this.shopService = shopService; }
    @PostMapping("/createOne")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop){
        shopService.addShop(shop);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Shop>> getAllShops(){
        List<Shop> shops = shopService.findAllShops();
        return new ResponseEntity<>(shops,HttpStatus.OK);
    }
    @GetMapping("/findCurrentEmployeesOfShop/{shopId}")
    public ResponseEntity<List<RoleInCareer>> getCurrentEmployeesOfShop(@PathVariable("shopId") Long shopId){
        return new ResponseEntity<>(this.shopService.findCurrentEmployeesOfShop(shopId),HttpStatus.OK);
    }
    @GetMapping("/findOldEmployeesOfShop/{shopId}")
    public ResponseEntity<?> getOldEmployeesOfShop(@PathVariable("shopId") Long shopId){
        try {
            return new ResponseEntity<>(this.shopService.findOldEmployeesOfShop(shopId),HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findAdminOfShop/{shopId}")
    public ResponseEntity<?> getShopAdmin(@PathVariable("shopId") Long shopId){
        try {
            return new ResponseEntity<>(shopService.findAdminOfShop(shopId),HttpStatus.OK);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite lors de la recherche du manager de boutique",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addEmployeeToShop")
    public ResponseEntity<?> addEmployeeToShop(@RequestBody AddEmployeeToShopRecord addEmployeeToShopRecord){
        try {
            return new ResponseEntity<>(shopService.addEmployeeToShop(addEmployeeToShopRecord),HttpStatus.OK);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/removeEmployeeFromShop/{employeeId}/{shopId}")
    public ResponseEntity<?> removeEmployeeFromShop(@PathVariable("employeeId") Long employeeId, @PathVariable("shopId") Long shopId){
        try {
            return new ResponseEntity<>(shopService.removeEmployeeFromShop(employeeId,shopId),HttpStatus.OK);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/mutateEmployee")
    public ResponseEntity<?> mutateEmployee(@RequestBody MutateEmployeeRecord mutateEmployeeRecord){
        try {
            Employee employee = shopService.MutateEmployee(mutateEmployeeRecord);
            return new ResponseEntity<>(employee,HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<?> findShopById(@PathVariable("id") Long Id){
        try {
            Shop shop = shopService.findShopById(Id);
            return new ResponseEntity<>(shop,HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<Shop> updateShopById(@PathVariable("id") Long Id,@RequestBody Shop updatedShop){
        Shop shop = shopService.updateShopById(updatedShop,Id);
        return new ResponseEntity<>(shop,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<Shop> deleteShopById(@PathVariable("id") Long Id){
        shopService.deleteShopById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
