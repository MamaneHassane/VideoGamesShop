package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.ShopNotFoundException;
import com.videogamesshop.vgs_package.model.entities.Shop;
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
        return shopRepository.findById(Id)
                             .orElseThrow(()-> new ShopNotFoundException(Id));
    }
    public Shop updateShopById(Shop updatedShop, Long Id){
        return shopRepository.findById(Id).map(
                shop->{
                    shop.setShopName(updatedShop.getShopName());
                    shop.setRentsOfShop(updatedShop.getRentsOfShop());
                    shop.setManager(updatedShop.getManager());
                    shop.setEmployeesWhoWorkedHere(updatedShop.getEmployeesWhoWorkedHere());
                    return shopRepository.save(shop);
                }
        ).orElseThrow(()->new ShopNotFoundException(Id));
    }
    public void deleteShopById(Long id){
        shopRepository.deleteById(id);
    }
}
