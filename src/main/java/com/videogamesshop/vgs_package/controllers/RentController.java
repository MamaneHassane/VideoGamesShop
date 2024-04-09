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

import java.util.List;

@RestController
@RequestMapping("/rents")
@PreAuthorize("hasAnyAuthority({'EMPLOYEE,ADMIN,MODERATOR'})")
public class RentController {
    private final RentService rentService;
    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/test")
    public String rentsControllerWork(){
        return "Rents controller works fine";
    }

    @PostMapping("/createrent")
    public ResponseEntity<?> createRent(@RequestBody CreateRentRecord createRentRecord){
        try {
            return new ResponseEntity<Rent>(rentService.createRent(createRentRecord), HttpStatus.CREATED);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addgametorent")
    public ResponseEntity<?> addGameToRent(@RequestBody AddGameToRentRecord addGameToRentRecord){
        try {
            Rent rentDone = rentService.addGameToRent(addGameToRentRecord);
            if(rentDone != null) return new ResponseEntity<Rent>(rentDone, HttpStatus.CREATED);
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.EXPECTATION_FAILED);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/removegamefromrent/{rentId}/{gameId}")
    public ResponseEntity<?> deleteGameFromRent(@PathVariable("rentId") long rentId, @PathVariable("gameId") long gameId){
        try {
            rentService.deleteGameFromRent(rentId,gameId);
            return new ResponseEntity<>("Jeu supprimé du prêt",HttpStatus.OK);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/confirmrent/{rentId}")
    public ResponseEntity<?> confirmRent(@PathVariable("rentId") Long rentId){
        try {
            return new ResponseEntity<>(rentService.confirmRent(rentId), HttpStatus.CREATED);
        } catch(Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/returnrent/{rentId}")
    public ResponseEntity<?> returnRent(@PathVariable("rentId") Long rentId){
        try {
            return new ResponseEntity<>(rentService.returnRent(rentId), HttpStatus.CREATED);
        } catch(Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findrentsofcustomer/{customerId}")
    public ResponseEntity<?> findCustomerRents(@PathVariable("customerId") Long customerId){
        try {
            return new ResponseEntity<>(rentService.findRentsByCustomerId(customerId), HttpStatus.OK);
        } catch(Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("Une erreur s'est produite",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
