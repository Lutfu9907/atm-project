package com.lutfudolay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lutfudolay.dto.AdminDTO;
import com.lutfudolay.mapper.AdminMapper;
import com.lutfudolay.service.IAdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    // Tek bir admin getir
    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id)
                .map(admin -> ResponseEntity.ok(AdminMapper.toDTO(admin)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Tüm adminleri getir
    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins()
                .stream()
                .map(AdminMapper::toDTO)
                .toList();
        return ResponseEntity.ok(admins);
    }
}