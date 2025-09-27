package com.lutfudolay.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lutfudolay.entities.Admin;
import com.lutfudolay.repository.AdminRepository;
import com.lutfudolay.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Optional<Admin> getAdminById(Long id) {
		return adminRepository.findById(id);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}

}
