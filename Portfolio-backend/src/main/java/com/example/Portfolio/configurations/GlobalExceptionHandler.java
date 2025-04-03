package com.example.Portfolio.configurations;

import com.example.Portfolio.dtos.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseStatusException(ResponseStatusException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(e.getReason());
        errorResponse.setError(e.getBody().getTitle());
        errorResponse.setStatus(e.getBody().getStatus());
        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }
}
