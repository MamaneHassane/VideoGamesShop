package com.videogamesshop.vgs_package.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Rent {
    // L'identifiant du prêt dans la base de données
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    // Un prêt appartient à un seul client
    @ManyToOne
    Customer customer;
    // Un prêt à une date de début
    LocalDate startDate;

    // Un prêt est effectué par un seul agent
    @ManyToOne
    Employee employee;
    // Un prêt est effectuée dans un shop
    @ManyToOne
    Shop shop;
    // Un prêt contient une liste de copies de jeux
    @OneToMany(mappedBy = "rent")
    List<VideoGameCopy> videoGameCopies = new ArrayList<>();
}
