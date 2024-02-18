package com.videogamesshop.vgs_package.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){
        super("The customer with Identifier "+ id +"cannot be found...");
    }
    public CustomerNotFoundException(String userName, String password){
        super("The customer with username "+userName+"and password "+password+" was not found");
    }
}
