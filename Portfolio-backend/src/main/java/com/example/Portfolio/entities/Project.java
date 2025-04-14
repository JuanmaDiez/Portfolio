package com.example.Portfolio.entities;

import com.example.Portfolio.dtos.ProjectDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "projects")
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title_esp")
    private String titleEsp;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "description_esp")
    private String descriptionEsp;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "image")
    private String image;

    @Column(name = "personal")
    private Boolean personal;

    @Column(name = "site")
    private String site;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "project_technologies",
            joinColumns =  @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();

    public Project() {}

    public Project(ProjectDTO projectDTO) {
        this.titleEsp = projectDTO.getTitleEsp();
        this.titleEn = projectDTO.getTitleEn();
        this.descriptionEsp = projectDTO.getDescriptionEsp();
        this.descriptionEn = projectDTO.getDescriptionEn();
        this.image = projectDTO.getImage();
        this.personal = projectDTO.isPersonal();
        this.site = projectDTO.getSite();
        this.code = projectDTO.getCode();
    }

    public Project(String titleEsp, String titleEn, String descriptionEsp, String descriptionEn, String image, Set<Technology> technologies, Boolean personal, String site, String code) {
        this.titleEsp = titleEsp;
        this.titleEn = titleEn;
        this.descriptionEsp = descriptionEsp;
        this.descriptionEn = descriptionEn;
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

    public String getTitleEsp() {
        return this.titleEsp;
    }

    public void setTitleEsp(String titleEsp) {
        this.titleEsp = titleEsp;
    }

    public String getTitleEn() {
        return this.titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDescriptionEsp() {
        return this.descriptionEsp;
    }

    public void setDescriptionEsp(String descriptionEsp) {
        this.descriptionEsp = descriptionEsp;
    }

    public String getDescriptionEn() {
        return this.descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }


    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Technology> getTechnologies() {
        return this.technologies;
    }

    public void setTechnologies(Set<Technology> technologies) {
        this.technologies = technologies;
    }

    public void addTechnologies(Set<Technology> technologies) {
        this.technologies.addAll(technologies);
    }

    public void removeTechnologies(Set<Technology> technologies) {
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
