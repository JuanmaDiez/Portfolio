package com.example.Portfolio.controllers;

import com.example.Portfolio.dtos.ProjectDTO;
import com.example.Portfolio.dtos.SuccessResponseDTO;
import com.example.Portfolio.services.ProjectService;
import com.example.Portfolio.utils.SuccessMessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<List<ProjectDTO>>> get() {
        List<ProjectDTO> projects = this.projectService.getProjects();
        SuccessResponseDTO<List<ProjectDTO>> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.SUCCESS);
        response.setData(projects);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<ProjectDTO>> index(@PathVariable Long id) {
        ProjectDTO project = this.projectService.getProject(id);
        SuccessResponseDTO<ProjectDTO> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.SUCCESS);
        response.setData(project);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<ProjectDTO>> store(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO newProject = this.projectService.createProject(projectDTO);
        SuccessResponseDTO<ProjectDTO> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.PROJECT_CREATED);
        response.setData(newProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<ProjectDTO>> edit(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO project = this.projectService.editProject(id, projectDTO);
        SuccessResponseDTO<ProjectDTO> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.PROJECT_EDITED);
        response.setData(project);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<String>> delete(@PathVariable Long id) {
        this.projectService.deleteProject(id);
        SuccessResponseDTO<String> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.PROJECT_DELETED);
        return ResponseEntity.ok(response);
    }
}
