package com.videogamesshop.vgs_package.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.videogamesshop.vgs_package.security.entities.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties({"rolesOccupied","rentsDone"})
@Entity
public class Employee extends UserInfo {
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @JsonBackReference("role_employee")
    List<RoleInCareer> rolesOccupied;
    // Un employé effectue plusieurs prêts
    // qui ne sont pas chargés quand l'employé est chargé
    @OneToMany(mappedBy = "employee")
    List<Rent> rentsDone;
}
