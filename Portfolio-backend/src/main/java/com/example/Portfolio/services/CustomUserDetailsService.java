package com.example.Portfolio.services;

import com.example.Portfolio.entities.Admin;
import com.example.Portfolio.repositories.AdminRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    public CustomUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Admin admin = this.adminRepository.findAdminByUsernameOrEmail(usernameOrEmail).orElseThrow();
        return new User(admin.getEmail(), admin.getPassword(),
                admin.isEnabled(), admin.isAccountNonExpired(),
                admin.isAccountNonLocked(), admin.isCredentialsNonLocked(),
                new ArrayList<>());
    }
}
