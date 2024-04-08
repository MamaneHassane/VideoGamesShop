package com.videogamesshop.vgs_package.service.helpers;

import java.time.LocalDate;

public class ShopServiceHelper {
    public static String getMutationMessage(String employeeName, String startShop, String destinationShop, LocalDate startDate){
        return "Employé "+
                employeeName +
                " muté avec succès de " +
                startShop +
                "vers " +
                destinationShop +
                "à partir de " +
                startDate;
    }
}
