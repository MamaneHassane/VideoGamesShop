package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.GameConsole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameConsoleRepository extends JpaRepository<GameConsole,Long> {
    Optional<GameConsole> findGameConsoleById(Long Id);
    void deleteGameConsoleById(Long Id);
}
