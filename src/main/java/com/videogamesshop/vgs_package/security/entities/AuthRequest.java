package com.videogamesshop.vgs_package.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AuthRequest {
    String username;
    String password;
}

