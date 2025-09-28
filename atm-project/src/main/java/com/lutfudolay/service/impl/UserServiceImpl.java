package com.lutfudolay.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lutfudolay.entities.Account;
import com.lutfudolay.entities.Admin;
import com.lutfudolay.entities.User;
import com.lutfudolay.repository.AccountRepository;
import com.lutfudolay.repository.AdminRepository;
import com.lutfudolay.repository.UserRepository;
import com.lutfudolay.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public User registerUser(User user) {
	    Admin defaultAdmin = adminRepository.findById(1L)
	            .orElseThrow(() -> new RuntimeException("Admin not found"));

	    user.setAdmin(defaultAdmin);
	    User savedUser = userRepository.save(user);

	    Account account = new Account();
	    account.setBalance(0.0);  //bakiye
	    account.setUser(savedUser);
	    accountRepository.save(account);

	    return savedUser;
	}


	@Override
	public Optional<User> getUserById(Long id) {
		
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}
}
