package com.videogamesshop.vgs_package.model.entities;


import com.videogamesshop.vgs_package.model.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Admin {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long Id;
    @Column(nullable = false)
    String adminName;
    @Column(nullable = false)
    String adminCode;
    @Column(nullable = false)
    String adminPassword;
    LocalDate customerDateOfBirth;
    @Enumerated
    Role role;
}
