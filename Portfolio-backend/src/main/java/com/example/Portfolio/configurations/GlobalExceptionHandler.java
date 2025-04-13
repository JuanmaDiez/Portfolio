package com.example.Portfolio.configurations;

import com.example.Portfolio.dtos.ErrorResponseDTO;
import com.example.Portfolio.utils.ConstantUtils;
import com.example.Portfolio.utils.ErrorMessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(ErrorMessageUtils.PATH_VARIABLE_ERROR);
        errorResponse.setError(HttpStatus.BAD_REQUEST.name());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        if (e.getMessage().toLowerCase().contains(ConstantUtils.JSON_PARSE_ERROR))
            errorResponse.setMessage(ErrorMessageUtils.TECHNOLOGIES_PARSE_ERROR);
        else
            errorResponse.setMessage(e.getMessage().split(":")[0]);
        errorResponse.setError(HttpStatus.BAD_REQUEST.name());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setError(HttpStatus.UNAUTHORIZED.name());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
