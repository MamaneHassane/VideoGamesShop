package com.videogamesshop.vgs_package.model;

import com.videogamesshop.vgs_package.model.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Customer implements Serializable {
    // L'identifiant du client dans la base de données
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Le prénom du client
    String firstName;
    // Le nom du client
    String lastName;
    // La date de naissance
    LocalDate dateOfBirth;
    // Un client à un montant dans son compte
    double moneyBalance;
    // Un client à un nombre de cartouches, qui peuvent être converties en argent
    int bullets;
    // Un client fait plusieurs prêts
    @OneToMany(mappedBy = "customer")
    List<Rent> rentsDone = new ArrayList<>();
}
