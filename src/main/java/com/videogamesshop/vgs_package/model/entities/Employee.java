package com.videogamesshop.vgs_package.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String firstName;
    String lastName;
    // Un employé à un historique de roles dans sa carrière
    @OneToMany(mappedBy = "employee")
    List<RoleInCareer> rolesOccupied;
    // Un employé travaille dans un seul shop
    @ManyToOne
    Shop shop;
    // Un employé effectue plusieurs prêts
    // qui ne sont pas chargés quand l'employé est chargé
    @OneToMany(mappedBy = "employee")
    List<Rent> rentsDone;
}
