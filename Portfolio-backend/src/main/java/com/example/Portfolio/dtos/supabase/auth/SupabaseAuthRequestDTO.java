package com.example.Portfolio.dtos.supabase.auth;

public class SupabaseAuthRequestDTO {
    private String email;
    private String password;

    public SupabaseAuthRequestDTO() {

    }

    public SupabaseAuthRequestDTO(String email, String password) {

    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
