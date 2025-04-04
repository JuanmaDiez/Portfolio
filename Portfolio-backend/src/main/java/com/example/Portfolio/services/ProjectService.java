package com.example.Portfolio.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.ProjectDTO;
import com.example.Portfolio.entities.Project;
import com.example.Portfolio.repositories.ProjectRepository;
import com.example.Portfolio.utils.ErrorMessageUtil;
import com.example.Portfolio.utils.ProjectUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getProjects() {
        List<Project> projects = this.projectRepository.findAll();

        if (projects.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.NO_PROJECTS);

        List<ProjectDTO> projectDTOS = new ArrayList<>();

        for (Project project : projects) {
            projectDTOS.add(new ProjectDTO(project));
        }

        return projectDTOS;
    }

    public ProjectDTO getProject(Long id) {
        Project project = this.projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.PROJECT_NOT_FOUND));

        return new ProjectDTO(project);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        if (ProjectUtil.checkProductDTO(projectDTO))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.INSUFFICIENT_DATA);

        Project project = new Project(projectDTO);

        try {
            this.projectRepository.save(project);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage().contains("projects_unique") ? ErrorMessageUtil.TITLE_ALREADY_EXISTS : ErrorMessageUtil.CONSTRAINT_ERROR);
        }

        return new ProjectDTO(project);
    }

    public ProjectDTO editProject(Long id, ProjectDTO projectDTO) {
        Project project = this.projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.PROJECT_NOT_FOUND));

        boolean isEdited = false;

        if (!StringUtil.isNullOrEmpty(projectDTO.getTitle())) {
            project.setTitle(projectDTO.getTitle());
            isEdited = true;
        }

        if (!StringUtil.isNullOrEmpty(projectDTO.getDescription())) {
            project.setDescription(projectDTO.getDescription());
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
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.CODE_NOT_PERSONAL);

            project.setCode(projectDTO.getCode());
            isEdited = true;
        }


        if (projectDTO.getTechnologies() != null && !projectDTO.getTechnologies().isEmpty())  {
            project.addTechnologies(projectDTO.getTechnologies());
            isEdited = true;
        }

        if (isEdited) {
            try {
                this.projectRepository.save(project);
            } catch (DataIntegrityViolationException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.TITLE_ALREADY_EXISTS);
            }
        }

        return new ProjectDTO(project);
    }

    public void deleteProject(Long id) {
        Project project = this.projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.PROJECT_NOT_FOUND)
        );

        this.projectRepository.delete(project);
    }
}
