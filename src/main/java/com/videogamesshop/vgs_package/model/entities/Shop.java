package com.videogamesshop.vgs_package.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
// @JsonIgnoreProperties({"employeesWhoWorkedHere","rentsOfShop"})
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String shopName;
    // Un shop a été le lieu de travail de plusieurs employées
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    @JsonManagedReference("role_shop")
    List<RoleInCareer> employeesWhoWorkedHere = new ArrayList<>();
    // Un shop fait plusieurs rent
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    @JsonManagedReference("shop_rent")
    List<Rent> rentsOfShop = new ArrayList<>();
}
