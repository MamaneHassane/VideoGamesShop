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
    public ResponseEntity<?> getAllVideoGames(){
        try {

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<VideoGame> videoGames= videoGameService.findAllVideoGame();
        return new ResponseEntity<>(videoGames, HttpStatus.OK);
    }
    @PostMapping("/addCopy/{videoGameId}")
    public ResponseEntity<?> addNewCopyToGame(@PathVariable("videoGameId") Long videoGameId){
        try {
            if(videoGameService.addNewCopyToGame(videoGameId).get())
                return new ResponseEntity<>("Copie ajouté avec succès",HttpStatus.ACCEPTED);
            return new ResponseEntity<>("Erreur lors de l'ajout de la copie",HttpStatus.EXPECTATION_FAILED);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<?> getVideoGameById(@PathVariable Long id){
        try {
            VideoGame videoGame = videoGameService.findVideoGameById(id);
            return new ResponseEntity<>(videoGame,HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/createOne")
    public ResponseEntity<?> createVideoGame(@RequestPart("videogame") String createVideoGameString,
                                             @RequestPart("videogameimage")MultipartFile videoGameImage) throws Exception {
        try {
            VideoGame videoGame = videoGameService.addVideoGame(createVideoGameString,videoGameImage);
            return new ResponseEntity<>(videoGame,HttpStatus.CREATED);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<?> updateVideoGameById(@RequestBody UpdateVideoGameRecord updatedVideoGameRecord, @PathVariable("id") Long id){
        try {
            VideoGame videoGame = videoGameService.updateVideoGameById(updatedVideoGameRecord,id);
            return new ResponseEntity<>(videoGame,HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<?> deleteVideoGameById(@PathVariable("id") Long id){
        try {
            videoGameService.deleteVideoGameById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}