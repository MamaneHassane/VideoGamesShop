package com.videogamesshop.vgs_package.model;

import com.videogamesshop.vgs_package.model.Enums.ConsoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class GameConsole {
    // L'identifiant de la console dans la base de données
    @Id
    @GeneratedValue
    Long id;
    // Le nom de la console
    @Enumerated
    ConsoleName consoleName;
    // La date de parution
    int year;
    // La description de la console
    String description;
    // Une game console possède plusieurs copies
    @OneToMany(mappedBy = "gameConsole")
    List<GameConsoleCopy> copies = new ArrayList<>();
}
