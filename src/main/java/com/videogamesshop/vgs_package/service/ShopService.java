package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.ShopNotFoundException;
import com.videogamesshop.vgs_package.model.Shop;
import com.videogamesshop.vgs_package.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopService {
    private final ShopRepository shopRepository;
    @Autowired
    public ShopService(ShopRepository shopRepository){ this.shopRepository = shopRepository; }
    public void addShop(Shop shop){
        shopRepository.save(shop);
    }
    public List<Shop> findAllShops(){
        return shopRepository.findAll();
    }
    public Shop findShopById(Long Id){
        return shopRepository.findShopById(Id)
                .orElseThrow(()-> new ShopNotFoundException(Id));
    }
    public Shop updateShopById(Shop updatedShop, Long Id){
        return shopRepository.findById(Id).map(
                shop->{
                    shop.setManagerId(updatedShop.getManagerId());
                    shop.setShopLocationName(updatedShop.getShopLocationName());
                    return shopRepository.save(shop);
                }
        ).orElseThrow(()->new ShopNotFoundException(Id));
    }
    public void deleteShopById(Long Id){
        shopRepository.deleteShopsById(Id);
    }
}
