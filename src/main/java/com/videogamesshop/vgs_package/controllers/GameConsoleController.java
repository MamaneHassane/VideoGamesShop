package com.videogamesshop.vgs_package.controllers;

import com.videogamesshop.vgs_package.model.entities.GameConsole;
import com.videogamesshop.vgs_package.service.GameConsoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gameconsoles")
public class GameConsoleController {
    private final GameConsoleService gameConsoleService;
    public GameConsoleController(GameConsoleService gameConsoleService){ this.gameConsoleService = gameConsoleService; }
    @PostMapping("/createOne")
    public ResponseEntity<GameConsole> createGameConsole(@RequestBody GameConsole gameConsole){
        gameConsoleService.addGameConsole(gameConsole);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<GameConsole>> findAllGameConsoles(){
        List<GameConsole> gameConsoles = gameConsoleService.findAllGameConsoles();
        return new ResponseEntity<>(gameConsoles,HttpStatus.OK);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<GameConsole> findGameConsoleById(@PathVariable("id") Long Id){
        GameConsole gameConsole = gameConsoleService.findGameConsoleById(Id);
        return new ResponseEntity<>(gameConsole,HttpStatus.OK);
    }
    @PutMapping("/updateOne/{id}")
    public ResponseEntity<GameConsole> updateGameConsoleById(@PathVariable("id") Long Id, GameConsole updatedGameConsole){
        GameConsole gameConsole = gameConsoleService.updateGameConsoleById(updatedGameConsole,Id);
        return new ResponseEntity<>(gameConsole,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<GameConsole> deleteGameConsoleById(@PathVariable("id") Long Id){
        gameConsoleService.deleteGameConsoleById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
