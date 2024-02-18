package com.videogamesshop.vgs_package.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class EntityUtils {
    public static <T> T getEntityFromString(String entityString, Class<T> entityType) {
        T entity = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            entity = objectMapper.readValue(entityString, entityType);
        } catch (IOException err) {
            System.out.printf("Error", err);
        }
        return entity;
    }
}
