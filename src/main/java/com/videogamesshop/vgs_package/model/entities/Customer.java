package com.videogamesshop.vgs_package.model.entities;

import com.videogamesshop.vgs_package.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Customer extends User  implements Serializable {
    // Un client à un montant dans son compte
    double moneyBalance;
    // Un client à un nombre de cartouches, qui peuvent être converties en argent
    int bullets;
    // Un client fait plusieurs prêts
    @OneToMany(mappedBy = "customer")
    List<Rent> rentsDone = new ArrayList<>();
}
