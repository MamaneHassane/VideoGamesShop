package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.Enums.ConsoleName;
import com.videogamesshop.vgs_package.model.entities.GameConsole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameConsoleRepository extends JpaRepository<GameConsole,Long> {
    Optional<GameConsole> findByConsoleName(ConsoleName consoleName);
}
