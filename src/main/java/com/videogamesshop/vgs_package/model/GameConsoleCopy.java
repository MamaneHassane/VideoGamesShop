package com.videogamesshop.vgs_package.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class GameConsoleCopy {
    // L'identifiant de la copie dans la base de données
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    // Le numéro de série
    String serialNumber;
    // Une copie correspond à une seule console
    @ManyToOne
    GameConsole gameConsole;
}
