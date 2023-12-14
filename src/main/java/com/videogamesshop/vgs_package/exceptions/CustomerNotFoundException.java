package com.videogamesshop.vgs_package.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){
        super("The customer with Identifier "+ id +"cannot be found...");
    }
}
