package com.lutfudolay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lutfudolay.dto.UserDTO;
import com.lutfudolay.entities.User;
import com.lutfudolay.mapper.UserMapper;
import com.lutfudolay.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
	    List<UserDTO> users = userService.getAllUsers()
	            .stream()
	            .map(UserMapper::toDTO)
	            .toList();

	    return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
	    return userService.getUserById(id)
	            .map(user -> ResponseEntity.ok(UserMapper.toDTO(user)))
	            .orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user){
	    User savedUser = userService.registerUser(user);
	    return ResponseEntity.ok(UserMapper.toDTO(savedUser));
	}

}
