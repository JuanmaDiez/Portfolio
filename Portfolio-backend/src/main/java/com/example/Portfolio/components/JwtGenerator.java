package com.example.Portfolio.components;

import com.example.Portfolio.configurations.FilterExceptionHandler;
import com.example.Portfolio.configurations.JwtConfig;
import com.example.Portfolio.utils.ErrorMessageUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtGenerator {
    private final SecretKey key;

    public JwtGenerator(JwtConfig config) {
        this.key = Keys.hmacShaKeyFor(config.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                .claim("username", username)
                .signWith(this.key)
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = this.getClaims(token);
        return claims.getSubject();
    }

    public boolean validateToken(String token, HttpServletResponse response) throws IOException {
        try {
            Jwts.parser().verifyWith(this.key).build().parse(token);
        } catch (MalformedJwtException | SecurityException e) {
            FilterExceptionHandler.writeError(response, ErrorMessageUtil.MALFORMED_TOKEN, HttpStatus.UNAUTHORIZED);
            return false;
        } catch (ExpiredJwtException e) {
            FilterExceptionHandler.writeError(response, ErrorMessageUtil.EXPIRED_TOKEN, HttpStatus.UNAUTHORIZED);
            return false;
        } catch (UnsupportedJwtException e) {
            FilterExceptionHandler.writeError(response, ErrorMessageUtil.UNSUPPORTED_TOKEN, HttpStatus.UNAUTHORIZED);
            return false;
        } catch (IllegalArgumentException e) {
            FilterExceptionHandler.writeError(response, ErrorMessageUtil.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
            return false;
        }

        return true;
    }

    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(this.key).build().parseSignedClaims(token).getPayload();
    }
}
