package com.example.Portfolio.repositories;

import com.example.Portfolio.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
