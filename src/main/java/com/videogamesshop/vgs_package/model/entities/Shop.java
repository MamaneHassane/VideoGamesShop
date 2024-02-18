package com.videogamesshop.vgs_package.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String shopName;
    // Un shop a un seul manager
    @OneToOne
    Employee manager;
    // Un shop a été le lieu de travail de plusieurs employées
    @OneToMany(mappedBy = "shop")
    List<RoleInCareer> employeesWhoWorkedHere = new ArrayList<>();
    // Un shop fait plusieurs rent
    @OneToMany(mappedBy = "shop")
    List<Rent> rentsOfShop = new ArrayList<>();
}
