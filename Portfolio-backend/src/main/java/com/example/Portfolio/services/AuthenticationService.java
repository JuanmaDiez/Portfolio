package com.example.Portfolio.services;

import com.example.Portfolio.components.JwtGenerator;
import com.example.Portfolio.dtos.AuthenticationDTO;
import com.example.Portfolio.utils.AuthenticationUtil;
import com.example.Portfolio.utils.ErrorMessageUtil;
import org.flywaydb.core.internal.util.AbbreviationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    public String generateToken(AuthenticationDTO authenticationDTO) {
        if (AuthenticationUtil.checkAuthenticationDTO(authenticationDTO))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.INSUFFICIENT_DATA);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsernameOrEmail(), authenticationDTO.getPassword())
        );

        return jwtGenerator.generateToken(authenticationDTO.getUsernameOrEmail());
    }
}
