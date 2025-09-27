package com.lutfudolay.service;

import java.util.List;
import java.util.Optional;

import com.lutfudolay.entities.Admin;

public interface IAdminService {
	
	Admin createAdmin(Admin admin);           // yeni admin ekleme
    Optional<Admin> getAdminById(Long id);    // id ile admin bulma
    List<Admin> getAllAdmins();               // tüm adminler
    void deleteAdmin(Long id);                // admin silme
}
