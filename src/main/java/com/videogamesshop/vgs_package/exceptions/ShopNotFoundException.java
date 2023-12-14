package com.videogamesshop.vgs_package.exceptions;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(Long id) {
        super("Shop with id "+id+" was not found");
    }
}
