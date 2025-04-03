package com.example.Portfolio.dtos;

public class SuccessResponseDTO<T> extends ResponseDTO{
    private T data;

    public SuccessResponseDTO() {}

    public SuccessResponseDTO(String message, T data) {
        super(message);
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
