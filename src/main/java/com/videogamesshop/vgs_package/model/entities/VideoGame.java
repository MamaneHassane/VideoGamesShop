package com.videogamesshop.vgs_package.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class VideoGame {
    // L'identifiant du jeu
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    // Le nom du jeu
    String gameName;
    // La description du jeu
    String description;
    // La date de publication du jeu
    LocalDate publicationDate;
    // La jaquette du jeu
    byte[] gameImage;
    // Un jeu possède plusieurs copies
    // qui ne sont pas chargées quand on charge le jeu
    @OneToMany(mappedBy = "videoGame", fetch = FetchType.LAZY)
    List<VideoGameCopy> copies = new ArrayList<>(); // La liste des copies
    // Un jeu se trouve sur plusieurs consoles
    @ManyToMany(mappedBy = "videoGames")
    Set<GameConsole> gameConsoles = new HashSet<>();
}
