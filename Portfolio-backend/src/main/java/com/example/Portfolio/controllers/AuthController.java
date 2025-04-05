package com.example.Portfolio.controllers;

import com.example.Portfolio.components.JwtGenerator;
import com.example.Portfolio.dtos.AuthenticationDTO;
import com.example.Portfolio.dtos.SuccessResponseDTO;
import com.example.Portfolio.services.AuthenticationService;
import com.example.Portfolio.utils.SuccessMessageUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<String>> login(@RequestBody AuthenticationDTO authenticationDTO) {
        String token = this.authenticationService.generateToken(authenticationDTO);
        SuccessResponseDTO<String> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.LOGIN_SUCCESSFUL);
        response.setData(token);
        return ResponseEntity.ok(response);
    }
}
