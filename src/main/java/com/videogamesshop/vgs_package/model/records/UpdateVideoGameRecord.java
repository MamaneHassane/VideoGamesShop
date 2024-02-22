package com.videogamesshop.vgs_package.model.records;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

public record UpdateVideoGameRecord(
        String gameName,
        String description,
        LocalDate publicationDate,
        List<String> consoles
){
    public static UpdateVideoGameRecord fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,UpdateVideoGameRecord.class);
    }
}