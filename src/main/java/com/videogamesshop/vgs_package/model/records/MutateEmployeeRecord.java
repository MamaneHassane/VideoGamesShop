package com.videogamesshop.vgs_package.model.records;

import java.time.LocalDate;
import java.util.Date;

public record MutateEmployeeRecord(
        Long employeeId,
        Long startShopId,
        Long destinationShopId,
        LocalDate since
) {
}
