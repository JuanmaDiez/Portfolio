package com.example.Portfolio.controllers;

import com.example.Portfolio.dtos.SuccessResponseDTO;
import com.example.Portfolio.dtos.TechnologyDTO;
import com.example.Portfolio.services.TechnologyService;
import com.example.Portfolio.utils.SuccessMessageUtils;
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
@RequestMapping("/technologies")
public class TechnologyController {
    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<List<TechnologyDTO>>> index() {
        List<TechnologyDTO> technologies = this.technologyService.indexTechnologies();
        SuccessResponseDTO<List<TechnologyDTO>> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtils.SUCCESS);
        response.setData(technologies);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<TechnologyDTO>> get(@PathVariable Long id) {
        TechnologyDTO technology = this.technologyService.getTechnology(id);
        SuccessResponseDTO<TechnologyDTO> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtils.SUCCESS);
        response.setData(technology);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<TechnologyDTO>> store(@RequestBody TechnologyDTO technologyDTO) {
        TechnologyDTO technology = this.technologyService.createTechnology(technologyDTO);
        SuccessResponseDTO<TechnologyDTO> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtils.TECHNOLOGY_CREATED);
        response.setData(technology);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<TechnologyDTO>> edit(@PathVariable Long id, @RequestBody TechnologyDTO technologyDTO) {
        TechnologyDTO technology = this.technologyService.editTechnology(id, technologyDTO);
        SuccessResponseDTO<TechnologyDTO> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtils.TECHNOLOGY_EDITED);
        response.setData(technology);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<String>> delete(@PathVariable Long id) {
        this.technologyService.deleteTechnology(id);
        SuccessResponseDTO<String> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtils.TECHNOLOGY_DELETED);
        response.setData(null);
        return ResponseEntity.ok(response);
    }
}
