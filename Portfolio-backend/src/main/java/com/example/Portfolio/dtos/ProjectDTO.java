package com.example.Portfolio.dtos;

import com.example.Portfolio.entities.Project;

import java.time.LocalDateTime;
import java.util.Set;

public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Set<String> technologies;
    private Boolean personal;
    private LocalDateTime createdAt;

    public ProjectDTO() {}

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.image = project.getImage();
        this.technologies = project.getTechnologies();
        this.personal = project.isPersonal();
        this.createdAt = project.getCreatedAt();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<String> getTechnologies() {
        return this.technologies;
    }

    public void setTechnologies(Set<String> technologies) {
        this.technologies = technologies;
    }

    public Boolean isPersonal() {
        return this.personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
