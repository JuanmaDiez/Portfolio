package com.example.Portfolio.repositories;

import com.example.Portfolio.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT a from Admin a WHERE email = :usernameOrEmail OR username = :usernameOrEmail")
    Optional<Admin> findAdminByUsernameOrEmail(String usernameOrEmail);
}
