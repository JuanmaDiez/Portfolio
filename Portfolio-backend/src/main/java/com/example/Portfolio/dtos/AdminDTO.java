package com.example.Portfolio.dtos;

import com.example.Portfolio.entities.Admin;

public class AdminDTO {
    private Long id;

    private String email;

    private String username;

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
