package com.lutfudolay.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lutfudolay.entities.User;
import com.lutfudolay.repository.UserRepository;
import com.lutfudolay.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		
		return userRepository.findByUserName(username);
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
