package com.videogamesshop.vgs_package.model.records;

public record AddGameToRentRecord(
        Long rentId,
        Long videoGameId
) {
}
