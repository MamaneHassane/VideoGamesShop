package com.videogamesshop.vgs_package.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.videogamesshop.vgs_package.model.Enums.RentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    // Un prêt à une date de fin
    LocalDate endDate;
    // Un prêt à une date de remise
    LocalDate returnDate;
    // Un prêt à un coût
    double cost;
    // Un prêt est effectué par un seul agent
    @ManyToOne
    Employee employee;
    // Un prêt est effectuée dans un shop
    @ManyToOne
    Shop shop;
    // Un rent possède un statut : En cours ou remis
    RentStatus status;
    // Un prêt contient une liste de copies de jeux
    @ManyToMany(mappedBy = "rents")
    Set<VideoGameCopy> videoGameCopies = new HashSet<>();
}
