package com.videogamesshop.vgs_package.model.entities;

import com.videogamesshop.vgs_package.model.Enums.ConsoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    // L'image de la console
    byte[] consoleImage;
    // Une console à plusieurs jeux
    @ManyToMany
    @JoinTable(
            name = "console_game",
            joinColumns = @JoinColumn(name = "console_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    Set<VideoGame> videoGames = new HashSet<>();
}
