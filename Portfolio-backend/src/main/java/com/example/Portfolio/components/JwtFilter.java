package com.example.Portfolio.components;

import com.example.Portfolio.configurations.FilterExceptionHandler;
import com.example.Portfolio.utils.AuthenticationUtils;
import com.example.Portfolio.utils.ConstantUtils;
import com.example.Portfolio.utils.ErrorMessageUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter implements Filter {
    private final JwtGenerator jwtGenerator;

    public JwtFilter(@Autowired JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String authHeader = httpRequest.getHeader(ConstantUtils.AUTHORIZATION_HEADER);

        if (AuthenticationUtils.checkAuthenticationHeader(authHeader)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = authHeader.split(" ")[1];

        if (this.jwtGenerator.validateToken(token, (HttpServletResponse) servletResponse)) {
            String username = this.jwtGenerator.extractUsername(token);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    new User(username, "", Collections.emptyList()), null, Collections.emptyList()
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else return;

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
