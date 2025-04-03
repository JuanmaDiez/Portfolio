package com.example.Portfolio.dtos;

public class ErrorResponseDTO extends ResponseDTO {
    private String error;

    private int status;

    public ErrorResponseDTO() {}

    public ErrorResponseDTO(String message, String error, int status) {
        super(message);
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
