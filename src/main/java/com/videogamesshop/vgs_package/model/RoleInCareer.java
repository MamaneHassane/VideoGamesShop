package com.videogamesshop.vgs_package.model;

import com.videogamesshop.vgs_package.model.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class RoleInCareer {
    // L'identifiant de la période de carrière dans la base de données
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    // Le role
    Role role;
    // Le salaire
    double salary;
    // L'employé concerné
    @ManyToOne
    Employee employee;
    // Le shop
    @ManyToOne
    Shop shop;
    // La date de début de poste
    LocalDate since;
    // La date de fin de poste
    LocalDate to;
}
