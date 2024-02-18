package com.videogamesshop.vgs_package.model.entities;

import com.videogamesshop.vgs_package.model.Enums.VideoGameCopyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class VideoGameCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; // L'identifiant de la copie
    // Une copie correspond à un seul jeu
    @ManyToOne
    VideoGame videoGame; // Le jeu correspondant
    // Une copie à un statut
    VideoGameCopyStatus status;
    // Une copie est prêtée plusieurs fois
    @ManyToMany
    @JoinTable(
            name = "rents_copies",
            joinColumns = @JoinColumn(name = "copy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rent_id", referencedColumnName = "id")
    )
    Set<Rent> rents = new HashSet<>();
}
