package com.videogamesshop.vgs_package.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Employee {
    @Id @GeneratedValue
    private Long id;
    String firstName;
    String lastName;
    String employeeCode;
    Long shopCode;
}
