package com.example.Portfolio.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Set;

@Converter
public class JsonConverter implements AttributeConverter<Set<String>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set<String> technologies) {
        try {
            return mapper.writeValueAsString(technologies);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<String> convertToEntityAttribute(String technologies) {
        try {
            return mapper.readValue(technologies, new TypeReference<Set<String>>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
