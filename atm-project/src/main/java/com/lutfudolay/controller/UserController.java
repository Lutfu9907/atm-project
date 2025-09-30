package com.lutfudolay.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lutfudolay.dto.UserDTO;
import com.lutfudolay.dto.UserRegisterDTO;
import com.lutfudolay.entities.User;
import com.lutfudolay.mapper.UserMapper;
import com.lutfudolay.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
	    List<UserDTO> users = userService.getAllUsers()
	            .stream()
	            .map(user -> new UserDTO(
	            		user.getId(),
	            		user.getUsername(),
	            		user.getAccount() != null ? user.getAccount().getId() : null )).collect(Collectors.toList()); 

	    return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
	    return userService.getUserById(id)
	            .map(user -> ResponseEntity.ok(UserMapper.toDTO(user)))
	            .orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
	    
		User user = new User();
		user.setUsername(userRegisterDTO.getUsername());
		user.setPassword(userRegisterDTO.getPassword());
		
		User savedUser = userService.registerUser(user);
		
		UserDTO reponse = new UserDTO(
				savedUser.getId(),
				savedUser.getUsername(),
				savedUser.getAccount() != null ? savedUser.getAccount().getId() : null );
		
		return ResponseEntity.ok(reponse);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
