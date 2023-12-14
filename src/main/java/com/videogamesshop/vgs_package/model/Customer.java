package com.videogamesshop.vgs_package.model;

import com.videogamesshop.vgs_package.model.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String customerFirstName;
    String customerLastName;
    LocalDate customerDateOfBirth;
    String customerCode;
    @Enumerated
    Role role;
}
