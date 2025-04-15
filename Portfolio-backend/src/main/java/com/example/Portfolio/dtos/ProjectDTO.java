package com.example.Portfolio.dtos;

import com.example.Portfolio.entities.Project;
import com.example.Portfolio.entities.Technology;
import com.example.Portfolio.utils.ErrorMessageUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ProjectDTO {
    private Long id;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String titleEsp;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String titleEn;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String descriptionEsp;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String descriptionEn;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String image;

    private Set<Long> technologiesIds;

    private Set<TechnologyDTO> technologies = new HashSet<>();

    @NotNull(message = ErrorMessageUtils.NOT_EMPTY)
    private Boolean personal;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String site;

    private String code;

    private LocalDateTime createdAt;

    public ProjectDTO() {}

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.titleEsp = project.getTitleEsp();
        this.titleEn = project.getTitleEn();
        this.descriptionEsp = project.getDescriptionEsp();
        this.descriptionEn = project.getDescriptionEn();
        this.image = project.getImage();
        this.personal = project.isPersonal();
        this.site = project.getSite();
        this.code = project.getCode();
        this.createdAt = project.getCreatedAt();
        for (Technology technology : project.getTechnologies()) {
            this.technologies.add(new TechnologyDTO(technology));
        }
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

    public Set<Long> getTechnologiesIds() {
        return this.technologiesIds;
    }

    public void setTechnologiesIds(Set<Long> technologiesIds) {
        this.technologiesIds = technologiesIds;
    }

    public Set<TechnologyDTO> getTechnologies() {
        return this.technologies;
    }

    public void setTechnologies(Set<TechnologyDTO> technologies) {
        this.technologies = technologies;
    }

    public Boolean isPersonal() {
        return this.personal;
    }

    public void setPersonal(boolean personal) {
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
