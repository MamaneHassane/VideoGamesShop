package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
