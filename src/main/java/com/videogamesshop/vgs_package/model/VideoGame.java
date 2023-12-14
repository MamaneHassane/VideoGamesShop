package com.videogamesshop.vgs_package.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class VideoGame {
    @Id @GeneratedValue
    private Long id = 1L;
    public String gameName;

    public String description;
    LocalDate publicationDate;
}
