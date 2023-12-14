package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    Optional<Shop> findShopById(Long id);
    void deleteShopsById(Long Id);
}
