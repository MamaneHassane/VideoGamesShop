package com.videogamesshop.vgs_package.service.helpers;

import java.time.LocalDate;

public class ShopServiceHelpers {
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
