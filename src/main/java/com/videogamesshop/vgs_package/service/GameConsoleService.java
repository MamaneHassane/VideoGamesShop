package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.GameConsoleNotFoundException;
import com.videogamesshop.vgs_package.model.Enums.ConsoleName;
import com.videogamesshop.vgs_package.model.entities.GameConsole;
import com.videogamesshop.vgs_package.repository.GameConsoleRepository;
import com.videogamesshop.vgs_package.service.helpers.GameConsoleServiceHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.videogamesshop.vgs_package.utilities.ImageUtils.compressImage;
import static com.videogamesshop.vgs_package.utilities.ImageUtils.decompressImage;

@Service
@Transactional
public class GameConsoleService {
    private final GameConsoleRepository gameConsoleRepository;
    @Autowired
    public GameConsoleService(GameConsoleRepository gameConsoleRepository) { this.gameConsoleRepository = gameConsoleRepository; }
    public void addGameConsole(String gameConsoleString, MultipartFile gameConsoleImage) throws IOException {
        try {
            GameConsole gameConsole = GameConsoleServiceHelpers.fromJsonString(gameConsoleString);
            gameConsole.setConsoleImage(compressImage(gameConsoleImage.getBytes()));
            gameConsoleRepository.save(gameConsole);
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite pendant la création de la console de jeu :" + e.getMessage());
            throw new RuntimeException(e);
        }
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
        GameConsole gameConsole = gameConsoleRepository.findById(Id)
                .orElseThrow(()-> new GameConsoleNotFoundException(Id));
        gameConsole.setConsoleImage(decompressImage(gameConsole.getConsoleImage()));
        return gameConsole;
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
