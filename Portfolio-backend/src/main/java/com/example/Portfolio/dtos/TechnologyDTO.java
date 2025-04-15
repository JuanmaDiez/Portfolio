package com.example.Portfolio.dtos;

import com.example.Portfolio.entities.Technology;
import com.example.Portfolio.utils.ErrorMessageUtils;
import jakarta.validation.constraints.NotEmpty;

public class TechnologyDTO {
    private Long id;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String name;

    @NotEmpty(message = ErrorMessageUtils.NOT_EMPTY)
    private String icon;

    public TechnologyDTO() {}

    public TechnologyDTO(Long id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public TechnologyDTO(Technology technology) {
        this.id = technology.getId();
        this.name = technology.getName();
        this.icon = technology.getIcon();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
