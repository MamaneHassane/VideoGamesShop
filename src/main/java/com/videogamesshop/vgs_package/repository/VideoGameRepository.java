package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VideoGameRepository extends JpaRepository<VideoGame,Long> {
    Optional<VideoGame> findVideoGameById(Long Id);
    void deleteVideoGameById(Long Id);
    Long countById(Long Id);
    Long countByGameName(String GameName);
}
