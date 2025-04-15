package com.example.Portfolio.dtos;

import com.example.Portfolio.utils.ErrorMessageUtils;
import jakarta.validation.constraints.NotEmpty;

public class AuthenticationDTO {
    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String usernameOrEmail;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String password;

    public AuthenticationDTO() {}

    public AuthenticationDTO(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return this.usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
