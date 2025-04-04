package com.example.Portfolio.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.AdminDTO;
import com.example.Portfolio.entities.Admin;
import com.example.Portfolio.repositories.AdminRepository;
import com.example.Portfolio.utils.AdminUtil;
import com.example.Portfolio.utils.ConstantUtil;
import com.example.Portfolio.utils.ErrorMessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = this.adminRepository.findAll();

        if (admins.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.NO_ADMINS);

        List<AdminDTO> adminDTOs = new ArrayList<>();

        for (Admin admin : admins) {
            AdminDTO adminDTO = new AdminDTO(admin);
            adminDTOs.add(adminDTO);
        }

        return adminDTOs;
    }

    public AdminDTO getAdmin(Long id) {
        Admin admin = this.adminRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        ErrorMessageUtil.ADMIN_NOT_FOUND));
        return new AdminDTO(admin);
    }

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        if (
               AdminUtil.checkAdminDTO(adminDTO)
        )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.INSUFFICIENT_DATA);

        Admin admin = new Admin(adminDTO);

        try {
            this.adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage().toLowerCase().contains(ConstantUtil.EMAIL_UNIQUE_KEY) ?
                            ErrorMessageUtil.EMAIL_ALREADY_EXISTS :
                            e.getMessage().toLowerCase().contains(ConstantUtil.USERNAME_UNIQUE_KEY) ?
                            ErrorMessageUtil.USERNAME_ALREADY_EXISTS :
                           ErrorMessageUtil.CONSTRAINT_ERROR);
        }

        return new AdminDTO(admin);
    }

    public AdminDTO editAdmin(AdminDTO adminDTO, Long id) {
        Admin admin = this.adminRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.ADMIN_NOT_FOUND)
        );
        boolean isEdited = false;

        if (StringUtil.notNullNorEmpty(adminDTO.getEmail())) {
            if (AdminUtil.checkEmail(adminDTO.getEmail()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.INVALID_EMAIL);

            admin.setEmail(adminDTO.getEmail());
            isEdited = true;
        }

        if (StringUtil.notNullNorEmpty(adminDTO.getUsername())) {
            admin.setUsername(adminDTO.getUsername());
            isEdited = true;
        }

        if (StringUtil.notNullNorEmpty(adminDTO.getPassword())) {
            admin.setPassword(adminDTO.getPassword());
            isEdited = true;
        }

        if (isEdited) {
            try {
                this.adminRepository.save(admin);
            } catch (DataIntegrityViolationException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        e.getMessage().toLowerCase().contains(ConstantUtil.EMAIL_UNIQUE_KEY) ?
                                ErrorMessageUtil.EMAIL_ALREADY_EXISTS :
                                ErrorMessageUtil.USERNAME_ALREADY_EXISTS);
            }
        }

        return new AdminDTO(admin);
    }

    public void deleteAdmin(Long id) {
        Admin admin = this.adminRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.ADMIN_NOT_FOUND));

        this.adminRepository.delete(admin);
    }
}
