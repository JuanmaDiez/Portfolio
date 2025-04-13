package com.example.Portfolio.configurations;

import com.example.Portfolio.dtos.ErrorResponseDTO;
import com.example.Portfolio.utils.ConstantUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class FilterExceptionHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void writeError(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType(ConstantUtils.CONTENT_TYPE_APP_JSON);
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(message);
        errorResponse.setError(status.name());
        errorResponse.setStatus(status.value());
        mapper.registerModule(new JavaTimeModule());
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }
}
