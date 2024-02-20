package com.videogamesshop.vgs_package.model.entities;


import com.videogamesshop.vgs_package.security.entities.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Admin extends UserInfo {

}
