package com.example.Portfolio.dtos;

import java.time.LocalDateTime;

abstract class ResponseDTO {
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ResponseDTO() {}

    public ResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
