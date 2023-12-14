package com.videogamesshop.vgs_package.exceptions;

public class VideoGameNotFoundException extends RuntimeException{
    public  VideoGameNotFoundException(Long id){
        super("The video game with Identifier "+ id + "cannot be found...");
    }
}
