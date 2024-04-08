package com.videogamesshop.vgs_package.model.records;

import com.videogamesshop.vgs_package.model.Enums.Role;
import com.videogamesshop.vgs_package.model.entities.Employee;
import com.videogamesshop.vgs_package.security.entities.UserInfo;

import java.time.LocalDate;

public record AddEmployeeToShopRecord(
        Employee employee,
        Long shopId,
        double salary,
        LocalDate startDate,
        Role role
) {
}
