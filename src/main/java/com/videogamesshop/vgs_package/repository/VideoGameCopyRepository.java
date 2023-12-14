package com.videogamesshop.vgs_package.repository;

import com.videogamesshop.vgs_package.model.VideoGameCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoGameCopyRepository extends JpaRepository<VideoGameCopy,Long> {
    Optional<VideoGameCopy> findVideoGameCopyById(Long Id);
    void deleteVideoGameCopyById(Long Id);
}