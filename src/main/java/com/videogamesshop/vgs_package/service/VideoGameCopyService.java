package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.VideoGameCopyNotFoundException;
import com.videogamesshop.vgs_package.model.entities.VideoGameCopy;
import com.videogamesshop.vgs_package.repository.VideoGameCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VideoGameCopyService {
    private final VideoGameCopyRepository videoGameCopyRepository;
    @Autowired
    public VideoGameCopyService(VideoGameCopyRepository videoGameCopyRepository){ this.videoGameCopyRepository = videoGameCopyRepository; }
    public void addVideoGameCopy(VideoGameCopy videoGameCopy){
        videoGameCopyRepository.save(videoGameCopy);
    }
    public List<VideoGameCopy> findAllVideoGameCopy(){
        return videoGameCopyRepository.findAll();
    }
    public VideoGameCopy findVideoGameCopyById(Long Id){
        return videoGameCopyRepository.findById(Id).orElseThrow(()->new VideoGameCopyNotFoundException(Id));
    }
    public VideoGameCopy updateVideoGameCopyById(VideoGameCopy updatedVideoGameCopy, Long Id){
        return videoGameCopyRepository.findById(Id).map(
                videoGameCopy -> {
                    videoGameCopy.setRents(updatedVideoGameCopy.getRents());
                    return videoGameCopyRepository.save(videoGameCopy);
                }
        ).orElseThrow(()->new VideoGameCopyNotFoundException(Id));
    }

    public void deleteVideoGameCopyById(Long Id){
        videoGameCopyRepository.deleteById(Id);
    }
}
