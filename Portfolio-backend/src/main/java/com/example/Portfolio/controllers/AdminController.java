package com.example.Portfolio.controllers;

import com.example.Portfolio.dtos.AdminDTO;
import com.example.Portfolio.dtos.SuccessResponseDTO;
import com.example.Portfolio.entities.Admin;
import com.example.Portfolio.services.AdminService;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<List<AdminDTO>>> get() {
        List<AdminDTO> admins = this.adminService.getAllAdmins();
        SuccessResponseDTO<List<AdminDTO>> response = new SuccessResponseDTO<>();
        response.setData(admins);
        response.setMessage(SuccessMessageUtil.SUCCESS);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<AdminDTO>> index(@PathVariable Long id) {
        AdminDTO admin = this.adminService.getAdmin(id);
        SuccessResponseDTO<AdminDTO> response = new SuccessResponseDTO<>();
        response.setData(admin);
        response.setMessage(SuccessMessageUtil.SUCCESS);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<AdminDTO>> store(@RequestBody AdminDTO adminDTO) {
        AdminDTO newAdmin = this.adminService.createAdmin(adminDTO);
        SuccessResponseDTO<AdminDTO> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.ADMIN_CREATED);
        response.setData(newAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<AdminDTO>> patch(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        AdminDTO admin = this.adminService.editAdmin(adminDTO, id);
        SuccessResponseDTO<AdminDTO> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.ADMIN_EDITED);
        response.setData(admin);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<String>> delete(@PathVariable Long id) {
        this.adminService.deleteAdmin(id);
        SuccessResponseDTO<String> response = new SuccessResponseDTO<>();
        response.setMessage(SuccessMessageUtil.ADMIN_DELETED);
        return ResponseEntity.ok(response);
    }
}
