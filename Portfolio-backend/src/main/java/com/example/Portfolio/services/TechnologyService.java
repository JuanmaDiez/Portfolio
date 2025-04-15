package com.example.Portfolio.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.TechnologyDTO;
import com.example.Portfolio.entities.Technology;
import com.example.Portfolio.repositories.TechnologyRepository;
import com.example.Portfolio.utils.ConstantUtils;
import com.example.Portfolio.utils.ErrorMessageUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public List<TechnologyDTO> indexTechnologies() {
        List<Technology> technologies = this.technologyRepository.findAll();

        if (technologies.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.NO_TECHNOLOGIES);

        List<TechnologyDTO> technologiesDTOs = new ArrayList<>();

        for (Technology technology : technologies) {
            technologiesDTOs.add(new TechnologyDTO(technology));
        }

        return technologiesDTOs;
    }

    public TechnologyDTO getTechnology(Long id) {
        Technology technology = this.technologyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.TECHNOLOGY_NOT_FOUND)
        );

        return new TechnologyDTO(technology);
    }

    public TechnologyDTO createTechnology(TechnologyDTO technologyDTO) {
        Technology technology = new Technology(technologyDTO);

        try {
            this.technologyRepository.save(technology);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().toLowerCase().contains(ConstantUtils.NAME_UNIQUE_KEY))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.TECHNOLOGY_ALREADY_EXISTS);
            else throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageUtils.SERVER_ERROR);
        }

        return new TechnologyDTO(technology);
    }
    
    public TechnologyDTO editTechnology(Long id, TechnologyDTO technologyDTO) {
        Technology technology = this.technologyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.TECHNOLOGY_NOT_FOUND)
        );

        boolean isEdited = false;

        if (!StringUtil.isNullOrEmpty(technologyDTO.getName())) {
            technology.setName(technologyDTO.getName());
            isEdited = true;
        }

        if (!StringUtil.isNullOrEmpty(technologyDTO.getIcon())) {
            technology.setIcon(technologyDTO.getIcon());
            isEdited = true;
        }

        if (isEdited) {
            try {
                this.technologyRepository.save(technology);
            } catch (DataIntegrityViolationException e) {
                if (e.getMessage().toLowerCase().contains(ConstantUtils.NAME_UNIQUE_KEY))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.TECHNOLOGY_ALREADY_EXISTS);
                else
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageUtils.SERVER_ERROR);
            }
        }

        return new TechnologyDTO(technology);
    }

    public void deleteTechnology(Long id) {
        Technology technology = this.technologyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.TECHNOLOGY_NOT_FOUND)
        );

        this.technologyRepository.delete(technology);
    }
}
