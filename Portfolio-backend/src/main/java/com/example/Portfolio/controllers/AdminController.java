package com.example.Portfolio.controllers;

import com.example.Portfolio.dtos.AdminDTO;
import com.example.Portfolio.dtos.ResponseDTO;
import com.example.Portfolio.entities.Admin;
import com.example.Portfolio.services.AdminService;
import com.example.Portfolio.utils.SuccessMessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Admin>>> get() {
        List<Admin> admins = this.adminService.getAllAdmins();
        ResponseDTO<List<Admin>> response = new ResponseDTO<>();
        response.setData(admins);
        response.setMessage(SuccessMessageUtil.SUCCESS);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Admin>> index(@PathVariable Long id) {
        Admin admin = this.adminService.getAdmin(id);
        ResponseDTO<Admin> response = new ResponseDTO<>();
        response.setData(admin);
        response.setMessage(SuccessMessageUtil.SUCCESS);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Admin>> store(@RequestBody AdminDTO adminDTO) {
        Admin newAdmin = this.adminService.createAdmin(adminDTO);
        ResponseDTO<Admin> response = new ResponseDTO<>();
        response.setMessage(SuccessMessageUtil.ADMIN_CREATED);
        response.setData(newAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO<Admin>> patch(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        Admin admin = this.adminService.editAdmin(adminDTO, id);
        ResponseDTO<Admin> response = new ResponseDTO<>();
        response.setMessage(SuccessMessageUtil.ADMIN_EDITED);
        response.setData(admin);
        return ResponseEntity.ok(response);
    }
}
