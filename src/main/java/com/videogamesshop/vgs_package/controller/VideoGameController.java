package com.videogamesshop.vgs_package.controller;

import com.videogamesshop.vgs_package.model.VideoGame;
import com.videogamesshop.vgs_package.service.VideoGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videogames")
public class VideoGameController {

    private final VideoGameService videoGameService;

    public VideoGameController(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<VideoGame>> getAllCustomer(){
        List<VideoGame> videoGames= videoGameService.findAllVideoGame();
        return new ResponseEntity<>(videoGames, HttpStatus.OK);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<VideoGame> getVideoGameById(@PathVariable Long id){
        VideoGame videoGame = videoGameService.findVideoGameById(id);
        return new ResponseEntity<>(videoGame,HttpStatus.OK);
    }

    @PostMapping("/createOne")
    public ResponseEntity<VideoGame> createVideoGame(@RequestBody VideoGame videoGame) {
        videoGameService.addVideoGame(videoGame);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/updateOne/{id}")
    public ResponseEntity<VideoGame> updateVideoGameById(@RequestBody VideoGame updatedVideoGame,@PathVariable("id") Long id) {
        VideoGame videoGame = videoGameService.updateVideoGameById(updatedVideoGame,id);
        return new ResponseEntity<>(videoGame,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<VideoGame> deleteVideoGameById(@PathVariable("id") Long id) {
        videoGameService.deleteVideoGameById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}