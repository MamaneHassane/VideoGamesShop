package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findAdminById(Long id);
    void deleteAdminById(Long Id);
}
