package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.entities.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoGameRepository extends JpaRepository<VideoGame,Long> {
}
