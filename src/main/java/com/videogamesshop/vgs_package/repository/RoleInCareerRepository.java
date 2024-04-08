package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.Enums.Role;
import com.videogamesshop.vgs_package.model.entities.RoleInCareer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleInCareerRepository extends JpaRepository<RoleInCareer,Long> {
    public RoleInCareer findByShop_IdAndRoleAndUntilIsNull(Long shop_id, Role role);
}
