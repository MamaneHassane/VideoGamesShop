package com.videogamesshop.vgs_package.exceptions;

public class GameConsoleNotFoundException extends RuntimeException {
    public GameConsoleNotFoundException(Long id) {
        super("Game console with id "+id+" was not found");
    }
}
