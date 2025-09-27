package com.lutfudolay.service;

import java.util.List;
import java.util.Optional;

import com.lutfudolay.entities.User;

public interface IUserService {
	
	User registerUser(User user);             // yeni kullanıcı ekleme
    Optional<User> getUserById(Long id);      // id ile kullanıcı bulma
    Optional<User> getUserByUsername(String username); // username ile kullanıcı bulma
    List<User> getAllUsers();                 // tüm kullanıcılar
    void deleteUser(Long id);                 // kullanıcı silme
}
