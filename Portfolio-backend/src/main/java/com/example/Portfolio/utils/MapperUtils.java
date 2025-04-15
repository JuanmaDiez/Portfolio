package com.example.Portfolio.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MapperUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String writeValueAsString(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageUtils.SERVER_ERROR);
        }
    }

    public static <T> T writeValueAsResponse(String json, Class<T> response) {
        try {
            return mapper.readValue(json, response);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageUtils.SERVER_ERROR);
        }
    }
}
