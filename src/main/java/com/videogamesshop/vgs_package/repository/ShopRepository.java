package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}
