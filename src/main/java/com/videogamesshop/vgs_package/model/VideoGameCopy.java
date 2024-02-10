package com.videogamesshop.vgs_package.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class VideoGameCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; // L'identifiant de la copie
    // Une copie correspond à un seul jeu
    @ManyToOne
    VideoGame videoGame; // Le jeu correspondant
    // Une copie est dans un seul prêt à la fois
    @ManyToOne
    Rent rent;
}
