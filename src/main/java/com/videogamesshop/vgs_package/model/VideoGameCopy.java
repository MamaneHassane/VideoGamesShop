package com.videogamesshop.vgs_package.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class VideoGameCopy {
    @Id @GeneratedValue
    private Long id = 1L;
    private Long gameName;
    @Enumerated
    private Long ConsoleName;
}
