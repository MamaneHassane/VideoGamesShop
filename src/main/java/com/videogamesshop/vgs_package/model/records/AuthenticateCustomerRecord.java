package com.videogamesshop.vgs_package.model.records;

public record AuthenticateCustomerRecord(
        String userName,
        String password
) {}
