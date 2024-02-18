package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.entities.GameConsole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameConsoleRepository extends JpaRepository<GameConsole,Long> {

}
