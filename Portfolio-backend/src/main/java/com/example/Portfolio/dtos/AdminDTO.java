package com.example.Portfolio.dtos;

import com.example.Portfolio.entities.Admin;
import com.example.Portfolio.utils.ErrorMessageUtils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class AdminDTO {
    private Long id;

    @Email(message = ErrorMessageUtils.INVALID_EMAIL)
    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String email;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String username;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String password;

    public AdminDTO() {}

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.email = admin.getEmail();
        this.username = admin.getUsername();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
