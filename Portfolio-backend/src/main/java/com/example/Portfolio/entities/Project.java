package com.example.Portfolio.entities;

import com.example.Portfolio.dtos.ProjectDTO;
import com.example.Portfolio.utils.JsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "projects")
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Convert(converter = JsonConverter.class)
    @Column(name = "technologies", columnDefinition = "json")
    private Set<String> technologies;

    @Column(name = "personal")
    private Boolean personal;

    @Column(name = "site")
    private String site;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Project() {}

    public Project(ProjectDTO projectDTO) {
        this.title = projectDTO.getTitle();
        this.description = projectDTO.getDescription();
        this.image = projectDTO.getImage();
        this.technologies = projectDTO.getTechnologies();
        this.personal = projectDTO.isPersonal();
        this.site = projectDTO.getSite();
        this.code = projectDTO.getCode();
    }

    public Project(String title, String description, String image, Set<String> technologies, Boolean personal, String site, String code) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.technologies = technologies;
        this.personal = personal;
        this.site = site;
        this.code = code;
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

    public void addTechnologies(Set<String> technologies) {
        this.technologies.addAll(technologies);
    }

    public void removeTechnologies(Set<String> technologies) {
        this.technologies.removeAll(technologies);
    }

    public Boolean isPersonal() {
        return this.personal;
    }

    public void setPersonal(Boolean personal) {
        this.personal = personal;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
