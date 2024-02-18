package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.VideoGameCopy;
import com.videogamesshop.vgs_package.service.VideoGameCopyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videogamecopies")
public class VideoGameCopyController {
    private final VideoGameCopyService videoGameCopyService;
    public VideoGameCopyController(VideoGameCopyService videoGameCopyService){ this.videoGameCopyService = videoGameCopyService; }
    @PostMapping("/createOne")
    public ResponseEntity<VideoGameCopy> createVideoGameCopy(@RequestBody VideoGameCopy videoGameCopy){
        videoGameCopyService.addVideoGameCopy(videoGameCopy);
        return new ResponseEntity<VideoGameCopy>(HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<VideoGameCopy>> findAllVideoGameCopy(){
        List<VideoGameCopy> videoGamesCopies = videoGameCopyService.findAllVideoGameCopy();
        return new ResponseEntity<>(videoGamesCopies,HttpStatus.OK);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<VideoGameCopy> findVideoGameCopyById(@PathVariable("id") Long Id){
        VideoGameCopy videoGameCopy = videoGameCopyService.findVideoGameCopyById(Id);
        return new ResponseEntity<>(videoGameCopy,HttpStatus.OK);
    }
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<VideoGameCopy> updateVideoGameCopyById(@RequestBody VideoGameCopy updatedVideoGame, @PathVariable("id") Long Id){
        VideoGameCopy videoGameCopy = videoGameCopyService.updateVideoGameCopyById(updatedVideoGame,Id);
        return new ResponseEntity<>(videoGameCopy,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<VideoGameCopy> deleteVideoGameCopyById(@PathVariable("id") Long Id){
        videoGameCopyService.deleteVideoGameCopyById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
