package com.example.Portfolio.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.Portfolio.dtos.AdminDTO;
import com.example.Portfolio.entities.Admin;
import com.example.Portfolio.repositories.AdminRepository;
import com.example.Portfolio.utils.ConstantUtil;
import com.example.Portfolio.utils.ErrorMessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        List<Admin> admins = this.adminRepository.findAll();

        if (admins.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.NO_ADMINS);

        return admins;
    }

    public Admin getAdmin(Long id) {
        return this.adminRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        ErrorMessageUtil.ADMIN_NOT_FOUND));
    }

    public Admin createAdmin(AdminDTO adminDTO) {
        if (
                StringUtil.isNullOrEmpty(adminDTO.getEmail()) ||
                StringUtil.isNullOrEmpty(adminDTO.getUsername()) ||
                StringUtil.isNullOrEmpty(adminDTO.getPassword())
        )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtil.INSUFFICIENT_DATA);

        Admin admin = new Admin(adminDTO.getEmail(), adminDTO.getUsername(), adminDTO.getPassword());

        try {
            this.adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage().toLowerCase().contains(ConstantUtil.USERNAME) ?
                            ErrorMessageUtil.USERNAME_ALREADY_EXISTS :
                            ErrorMessageUtil.EMAIL_ALREADY_EXISTS);
        }

        return admin;
    }

    public Admin editAdmin(AdminDTO adminDTO, Long id) {
        Admin admin = this.adminRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessageUtil.ADMIN_NOT_FOUND)
        );

        if (StringUtil.notNullNorEmpty(adminDTO.getEmail())) admin.setEmail(adminDTO.getEmail());
        if (StringUtil.notNullNorEmpty(adminDTO.getUsername())) admin.setUsername(adminDTO.getUsername());
        if (StringUtil.notNullNorEmpty(adminDTO.getPassword())) admin.setEmail(adminDTO.getPassword());

        try {
            this.adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage().toLowerCase().contains(ConstantUtil.USERNAME) ?
                            ErrorMessageUtil.USERNAME_ALREADY_EXISTS :
                            ErrorMessageUtil.EMAIL_ALREADY_EXISTS);
        }

        return admin;
    }

    public void deleteAdmin(Long id) {
        this.adminRepository.deleteById(id);
    }
}
