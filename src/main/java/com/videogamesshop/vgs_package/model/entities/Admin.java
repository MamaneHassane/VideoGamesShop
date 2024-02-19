package com.videogamesshop.vgs_package.model.entities;


import com.videogamesshop.vgs_package.model.Enums.Role;
import com.videogamesshop.vgs_package.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Admin extends User {

}
