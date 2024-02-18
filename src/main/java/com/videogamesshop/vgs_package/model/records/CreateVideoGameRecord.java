package com.videogamesshop.vgs_package.model.records;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videogamesshop.vgs_package.model.Enums.ConsoleName;
import com.videogamesshop.vgs_package.model.entities.GameConsole;
import com.videogamesshop.vgs_package.model.entities.VideoGameCopy;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record CreateVideoGameRecord (
        String gameName,
        String description,
        LocalDate publicationDate,
        List<String> consoles
){
    public static CreateVideoGameRecord fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,CreateVideoGameRecord.class);
    }
}
