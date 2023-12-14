package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.GameConsoleNotFoundException;
import com.videogamesshop.vgs_package.model.GameConsole;
import com.videogamesshop.vgs_package.repository.GameConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameConsoleService {
    private final GameConsoleRepository gameConsoleRepository;
    @Autowired
    public GameConsoleService(GameConsoleRepository gameConsoleRepository) { this.gameConsoleRepository = gameConsoleRepository; }
    public void addGameConsole(GameConsole gameConsole){
        gameConsoleRepository.save(gameConsole);
    }
    public List<GameConsole> findAllGameConsoles(){
        return gameConsoleRepository.findAll();
    }
    public GameConsole findGameConsoleById(Long Id){
        return gameConsoleRepository.findGameConsoleById(Id)
                .orElseThrow(()-> new GameConsoleNotFoundException(Id));
    }
    public GameConsole updateGameConsoleById(GameConsole updatedGameConsole, Long Id){
        return gameConsoleRepository.findById(Id).map(
                gameConsole -> {
                    gameConsole.setConsoleName(updatedGameConsole.getConsoleName());
                    gameConsole.setYear(updatedGameConsole.getYear());
                    gameConsole.setDescription(updatedGameConsole.getDescription());
                    return gameConsoleRepository.save(gameConsole);
                }
        ).orElseThrow(()->new GameConsoleNotFoundException(Id));
    }
    public void deleteGameConsoleById(Long Id){
        gameConsoleRepository.deleteGameConsoleById(Id);
    }
}
