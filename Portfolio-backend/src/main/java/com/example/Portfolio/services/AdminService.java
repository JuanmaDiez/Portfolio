package com.example.Portfolio.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.AdminDTO;
import com.example.Portfolio.entities.Admin;
import com.example.Portfolio.repositories.AdminRepository;
import com.example.Portfolio.utils.AdminUtils;
import com.example.Portfolio.utils.ConstantUtils;
import com.example.Portfolio.utils.ErrorMessageUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder encoder) {
        this.adminRepository = adminRepository;
        this.encoder = encoder;
    }

    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = this.adminRepository.findAll();

        if (admins.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.NO_ADMINS);

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
                        ErrorMessageUtils.ADMIN_NOT_FOUND));
        return new AdminDTO(admin);
    }

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        if (
               AdminUtils.checkAdminDTO(adminDTO)
        )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.INSUFFICIENT_DATA);

        adminDTO.setPassword(encoder.encode(adminDTO.getPassword()));
        Admin admin = new Admin(adminDTO);

        try {
            this.adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage().toLowerCase().contains(ConstantUtils.EMAIL_UNIQUE_KEY) ?
                            ErrorMessageUtils.EMAIL_ALREADY_EXISTS :
                            e.getMessage().toLowerCase().contains(ConstantUtils.USERNAME_UNIQUE_KEY) ?
                            ErrorMessageUtils.USERNAME_ALREADY_EXISTS :
                           ErrorMessageUtils.CONSTRAINT_ERROR);
        }

        return new AdminDTO(admin);
    }

    public AdminDTO editAdmin(AdminDTO adminDTO, Long id) {
        Admin admin = this.adminRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ADMIN_NOT_FOUND)
        );
        boolean isEdited = false;

        if (StringUtil.notNullNorEmpty(adminDTO.getEmail())) {
            if (AdminUtils.checkEmail(adminDTO.getEmail()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.INVALID_EMAIL);

            admin.setEmail(adminDTO.getEmail());
            isEdited = true;
        }

        if (StringUtil.notNullNorEmpty(adminDTO.getUsername())) {
            admin.setUsername(adminDTO.getUsername());
            isEdited = true;
        }

        if (StringUtil.notNullNorEmpty(adminDTO.getPassword())) {
            admin.setPassword(encoder.encode(adminDTO.getPassword()));
            isEdited = true;
        }

        if (isEdited) {
            try {
                this.adminRepository.save(admin);
            } catch (DataIntegrityViolationException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        e.getMessage().toLowerCase().contains(ConstantUtils.EMAIL_UNIQUE_KEY) ?
                                ErrorMessageUtils.EMAIL_ALREADY_EXISTS :
                                ErrorMessageUtils.USERNAME_ALREADY_EXISTS);
            }
        }

        return new AdminDTO(admin);
    }

    public void deleteAdmin(Long id) {
        Admin admin = this.adminRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ADMIN_NOT_FOUND));

        this.adminRepository.delete(admin);
    }
}
