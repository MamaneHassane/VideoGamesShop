package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.Shop;
import com.videogamesshop.vgs_package.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;
    public ShopController(ShopService shopService) { this.shopService = shopService; }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @PostMapping("/createOne")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop){
        shopService.addShop(shop);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @GetMapping("/findAll")
    public ResponseEntity<List<Shop>> getAllShops(){
        List<Shop> shops = shopService.findAllShops();
        return new ResponseEntity<>(shops,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Shop> findShopById(@PathVariable("id") Long Id){
        Shop shop = shopService.findShopById(Id);
        return new ResponseEntity<>(shop,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<Shop> updateShopById(@PathVariable("id") Long Id,@RequestBody Shop updatedShop){
        Shop shop = shopService.updateShopById(updatedShop,Id);
        return new ResponseEntity<>(shop,HttpStatus.ACCEPTED);
    }
    @PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<Shop> deleteShopById(@PathVariable("id") Long Id){
        shopService.deleteShopById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
