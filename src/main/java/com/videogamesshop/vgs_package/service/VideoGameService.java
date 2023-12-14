package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.VideoGameNotFoundException;
import com.videogamesshop.vgs_package.model.VideoGame;
import com.videogamesshop.vgs_package.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VideoGameService {
    private final VideoGameRepository videoGameRepository;
    @Autowired
    public VideoGameService(VideoGameRepository videoGameRepository){ this.videoGameRepository = videoGameRepository; }
    public void addVideoGame(VideoGame videoGame){
        videoGameRepository.save(videoGame);
    }
    public List<VideoGame> findAllVideoGame(){
        return videoGameRepository.findAll();
    }
    public VideoGame findVideoGameById(Long Id){
        return videoGameRepository.findVideoGameById(Id).orElseThrow(()-> new VideoGameNotFoundException(Id));
    }
    public VideoGame updateVideoGameById(VideoGame updatedVideoGame, Long Id){
        return videoGameRepository.findById(Id).map(
                videoGame -> {
                    videoGame.setGameName(updatedVideoGame.getGameName());
                    videoGame.setDescription(updatedVideoGame.getDescription());
                    videoGame.setPublicationDate((updatedVideoGame.getPublicationDate()));
                    return videoGameRepository.save(videoGame);
                }
        ).orElseThrow(()-> new VideoGameNotFoundException(Id));
    }
    public void deleteVideoGameById(Long Id){
        videoGameRepository.deleteVideoGameById(Id);
    }
}
