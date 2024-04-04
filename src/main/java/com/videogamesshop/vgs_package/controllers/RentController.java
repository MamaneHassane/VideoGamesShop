package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.Rent;
import com.videogamesshop.vgs_package.model.records.AddGameToRentRecord;
import com.videogamesshop.vgs_package.model.records.CreateRentRecord;
import com.videogamesshop.vgs_package.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rents")
public class RentController {
    private final RentService rentService;
    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }
    @PreAuthorize("hasAnyAuthority({'EMPLOYEE,ADMIN,MODERATOR'})")
    @GetMapping("/test")
    public String rentsControllerWork(){
        return "Rents controller works fine";
    }
    @PreAuthorize("hasAnyAuthority({'EMPLOYEE,ADMIN,MODERATOR'})")
    @PostMapping("/makerent")
    public ResponseEntity<Rent> makeRent(@RequestBody CreateRentRecord createRentRecord){
        return new ResponseEntity<Rent>(rentService.createRent(createRentRecord), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority({'EMPLOYEE,ADMIN,MODERATOR'})")
    @PostMapping("/addgametorent")
    public ResponseEntity<?> addGameToRent(@RequestBody AddGameToRentRecord addGameToRentRecord){
        Rent rentDone = rentService.addGameToRent(addGameToRentRecord);
        if(rentDone != null) return new ResponseEntity<Rent>(rentDone, HttpStatus.CREATED);
        return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.EXPECTATION_FAILED);
    }
    @PreAuthorize("hasAnyAuthority({'EMPLOYEE,ADMIN,MODERATOR'})")
    @PostMapping("/removegamefromrent/{rentId}/{gameId}")
    public ResponseEntity<?> deleteGameFromRent(@PathVariable("rentId") long rentId, @PathVariable("gameId") long gameId){
        rentService.deleteGameFromRent(rentId,gameId);
        return new ResponseEntity<>("Jeu supprimé du prêt",HttpStatus.OK);
    }
}
