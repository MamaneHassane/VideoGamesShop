package com.videogamesshop.vgs_package.model;


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
    String adminName;
    String adminCode;
    String adminPassword;
    LocalDate customerDateOfBirth;
    @Enumerated
    Role role;
}
