package com.example.Portfolio.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.ProjectDTO;
import com.example.Portfolio.entities.Project;
import com.example.Portfolio.entities.Technology;
import com.example.Portfolio.repositories.ProjectRepository;
import com.example.Portfolio.repositories.TechnologyRepository;
import com.example.Portfolio.utils.ConstantUtils;
import com.example.Portfolio.utils.ErrorMessageUtils;
import com.example.Portfolio.utils.ProjectUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(ProjectRepository projectRepository, TechnologyRepository technologyRepository) {
        this.projectRepository = projectRepository;
        this.technologyRepository = technologyRepository;
    }

    public List<ProjectDTO> getProjects() {
        List<Project> projects = this.projectRepository.findAll();

        if (projects.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.NO_PROJECTS);

        List<ProjectDTO> projectDTOS = new ArrayList<>();

        for (Project project : projects) {
            projectDTOS.add(new ProjectDTO(project));
        }

        return projectDTOS;
    }

    public ProjectDTO getProject(Long id) {
        Project project = this.projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.PROJECT_NOT_FOUND));

        return new ProjectDTO(project);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        if (ProjectUtils.checkProductDTO(projectDTO))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.INSUFFICIENT_DATA);

        Project project = new Project(projectDTO);

        Set<Technology> technologies = new HashSet<>();
        projectDTO.getTechnologiesIds().forEach(technologyId ->
                technologies.add(this.technologyRepository.findById(technologyId).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.TECHNOLOGY_NOT_FOUND)
                )
        ));
        project.addTechnologies(technologies);

        try {
            this.projectRepository.save(project);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage().toLowerCase().contains(ConstantUtils.PROJECT_UNIQUE_KEY_ESP) ||
                    e.getMessage().toLowerCase().contains(ConstantUtils.PROJECT_UNIQUE_KEY_EN)
                    ? ErrorMessageUtils.TITLE_ALREADY_EXISTS : ErrorMessageUtils.CONSTRAINT_ERROR);
        }

        return new ProjectDTO(project);
    }

    public ProjectDTO editProject(Long id, ProjectDTO projectDTO) {
        Project project = this.projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.PROJECT_NOT_FOUND));

        boolean isEdited = false;

        if (!StringUtil.isNullOrEmpty(projectDTO.getTitleEsp())) {
            project.setTitleEsp(projectDTO.getTitleEsp());
            isEdited = true;
        }

        if (!StringUtil.isNullOrEmpty(projectDTO.getTitleEn())) {
            project.setTitleEn(projectDTO.getTitleEn());
            isEdited = true;
        }

        if (!StringUtil.isNullOrEmpty(projectDTO.getDescriptionEsp())) {
            project.setDescriptionEsp(projectDTO.getDescriptionEsp());
            isEdited = true;
        }

        if (!StringUtil.isNullOrEmpty(projectDTO.getDescriptionEn())) {
            project.setDescriptionEn(projectDTO.getDescriptionEn());
            isEdited = true;
        }

        if (!StringUtil.isNullOrEmpty(projectDTO.getImage())) {
            project.setImage(projectDTO.getImage());
            isEdited = true;
        }

        if (projectDTO.isPersonal() != null) {
            project.setPersonal(projectDTO.isPersonal());
            isEdited = true;
        }

        if (!StringUtil.isNullOrEmpty(projectDTO.getSite())) {
            project.setSite(projectDTO.getSite());
            isEdited = true;
        }

        if (!StringUtil.isNullOrEmpty(projectDTO.getCode())) {
            if (!project.isPersonal().booleanValue())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.CODE_NOT_PERSONAL);

            project.setCode(projectDTO.getCode());
            isEdited = true;
        }


        if (projectDTO.getTechnologiesIds() != null && !projectDTO.getTechnologiesIds().isEmpty())  {
            Set<Technology> technologies = new HashSet<>();
            projectDTO.getTechnologiesIds().forEach(technologyId ->
                    technologies.add(this.technologyRepository.findById(technologyId).orElseThrow(() ->
                                    new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.TECHNOLOGY_NOT_FOUND)
                            )
                    ));
            project.addTechnologies(technologies);
            isEdited = true;
        }

        if (isEdited) {
            try {
                this.projectRepository.save(project);
            } catch (DataIntegrityViolationException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.TITLE_ALREADY_EXISTS);
            }
        }

        return new ProjectDTO(project);
    }

    public ProjectDTO deleteTech(Long id, ProjectDTO projectDTO) {
        Project project = this.projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.PROJECT_NOT_FOUND));

        if (projectDTO.getTechnologies() == null || projectDTO.getTechnologies().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.INSUFFICIENT_DATA);

        Set<Technology> technologies = new HashSet<>();
        projectDTO.getTechnologiesIds().forEach(technologyId ->
                technologies.add(this.technologyRepository.findById(technologyId).orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.TECHNOLOGY_NOT_FOUND)
                        )
                ));
        project.removeTechnologies(technologies);

        this.projectRepository.save(project);

        return new ProjectDTO(project);
    }

    public void deleteProject(Long id) {
        Project project = this.projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.PROJECT_NOT_FOUND)
        );

        this.projectRepository.delete(project);
    }
}
