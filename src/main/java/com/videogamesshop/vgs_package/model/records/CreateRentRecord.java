package com.videogamesshop.vgs_package.model.records;

public record CreateRentRecord(
        Long customerId,
        Long shopId,
        Long employeeId,
        Long numberOfDays
) {
}
