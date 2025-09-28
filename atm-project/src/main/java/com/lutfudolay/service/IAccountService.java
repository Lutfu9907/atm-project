package com.lutfudolay.service;

import java.util.Optional;

import com.lutfudolay.entities.Account;

public interface IAccountService {
		
	Optional<Account> getAccountByUserId(Long userId);
    Account createAccount(Account account);
    void deposit(Long userId, Double amount);     // para yatırma
    void withdraw(Long userId, Double amount);    // para çekme
    void transfer(Long fromUserId, Long toUserId, Double amount); // havale
	Double getBalance(Long userId);
}
