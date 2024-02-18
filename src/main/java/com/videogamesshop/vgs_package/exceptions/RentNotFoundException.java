package com.videogamesshop.vgs_package.exceptions;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException(Long id){
        super("The rent with id "+ id +" was not found");
    }
}
