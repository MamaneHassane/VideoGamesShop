package com.videogamesshop.vgs_package.service.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videogamesshop.vgs_package.model.entities.GameConsole;

public class GameConsoleServiceHelpers {
    public static GameConsole fromJsonString(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, GameConsole.class);
    }
}
