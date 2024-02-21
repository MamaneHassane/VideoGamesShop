package com.videogamesshop.vgs_package.model.entities;

import com.videogamesshop.vgs_package.security.entities.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Employee extends UserInfo {
    @OneToMany(mappedBy = "employee")
    List<RoleInCareer> rolesOccupied;
    // Un employé effectue plusieurs prêts
    // qui ne sont pas chargés quand l'employé est chargé
    @OneToMany(mappedBy = "employee")
    List<Rent> rentsDone;
}
