package com.videogamesshop.vgs_package.exceptions;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(Long Id){
        super("The Admin with id "+Id+" cannot be found");
    }

}
