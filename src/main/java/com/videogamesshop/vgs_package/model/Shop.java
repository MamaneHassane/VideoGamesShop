package com.videogamesshop.vgs_package.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Shop {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id = 1L;
    String shopLocationName;
    String shopCode;
    Long managerId;
}
