package com.videogamesshop.vgs_package.model;

import com.videogamesshop.vgs_package.model.Enums.ConsoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class GameConsole {
    @Id @GeneratedValue
    private Long id = 1L;
    @Enumerated
    ConsoleName consoleName;
    int year;
    String description;
}
