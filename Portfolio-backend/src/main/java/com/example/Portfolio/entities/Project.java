package com.example.Portfolio.entities;

import com.example.Portfolio.converters.JsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;
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
    private boolean personal;

    public Project(String title, String description, String image, Set<String> technologies, boolean personal) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.technologies = technologies;
        this.personal = personal;
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

    public boolean isPersonal() {
        return this.personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }
}
