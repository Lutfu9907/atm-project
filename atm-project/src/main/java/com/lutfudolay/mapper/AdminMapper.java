package com.lutfudolay.mapper;

import com.lutfudolay.dto.AdminDTO;
import com.lutfudolay.entities.Admin;

public class AdminMapper {
    public static AdminDTO toDTO(Admin admin) {
        return new AdminDTO(
            admin.getId(),
            admin.getUsername(),
            admin.getUsers() != null ? admin.getUsers().size() : 0
        );
    }
}