package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.GameConsoleNotFoundException;
import com.videogamesshop.vgs_package.model.Enums.ConsoleName;
import com.videogamesshop.vgs_package.model.entities.GameConsole;
import com.videogamesshop.vgs_package.repository.GameConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Set<GameConsole> craftVideoGameCopiesListFromNames(List<String> names){
        Set<GameConsole> gameConsoles = new HashSet<>();
        for(String name : names){
            Optional<GameConsole> theGameConsole = gameConsoleRepository.findByConsoleName(ConsoleName.valueOf(name));
            theGameConsole.ifPresent(gameConsoles::add);
        }
        return gameConsoles;
    }
    public GameConsole findGameConsoleById(Long Id){
        return gameConsoleRepository.findById(Id)
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
        gameConsoleRepository.deleteById(Id);
    }
}
