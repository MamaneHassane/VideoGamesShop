package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.VideoGame;
import com.videogamesshop.vgs_package.model.records.UpdateVideoGameRecord;
import com.videogamesshop.vgs_package.service.VideoGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/videogames")
@PreAuthorize("hasAnyAuthority({'ADMIN,MODERATOR'})")
public class VideoGameController {
    private final VideoGameService videoGameService;
    public VideoGameController(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<VideoGame>> getAllVideoGames(){
        List<VideoGame> videoGames= videoGameService.findAllVideoGame();
        return new ResponseEntity<>(videoGames, HttpStatus.OK);
    }
    @PostMapping("/addCopy/{videoGameId}")
    public ResponseEntity<?> addNewCopyToGame(@PathVariable("videoGameId") Long videoGameId){
        if(videoGameService.addNewCopyToGame(videoGameId).get())
            return new ResponseEntity<>("Copie ajouté avec succès",HttpStatus.ACCEPTED);
        return new ResponseEntity<>("Erreur lors de l'ajout de la copie",HttpStatus.EXPECTATION_FAILED);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<VideoGame> getVideoGameById(@PathVariable Long id){
        VideoGame videoGame = videoGameService.findVideoGameById(id);
        return new ResponseEntity<>(videoGame,HttpStatus.OK);
    }
    @PostMapping("/createOne")
    public ResponseEntity<VideoGame> createVideoGame(@RequestPart("videogame") String createVideoGameString,
                                                     @RequestPart("videogameimage")MultipartFile videoGameImage) throws Exception {
        VideoGame videoGame = videoGameService.addVideoGame(createVideoGameString,videoGameImage);
        return new ResponseEntity<>(videoGame,HttpStatus.CREATED);
    }
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<VideoGame> updateVideoGameById(@RequestBody UpdateVideoGameRecord updatedVideoGameRecord, @PathVariable("id") Long id){
        VideoGame videoGame = videoGameService.updateVideoGameById(updatedVideoGameRecord,id);
        return new ResponseEntity<>(videoGame,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<VideoGame> deleteVideoGameById(@PathVariable("id") Long id){
        videoGameService.deleteVideoGameById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}