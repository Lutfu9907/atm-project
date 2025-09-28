package com.lutfudolay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lutfudolay.dto.TransactionDTO;
import com.lutfudolay.entities.Account;
import com.lutfudolay.mapper.TransactionMapper;
import com.lutfudolay.service.IAccountService;
import com.lutfudolay.service.ITransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private ITransactionService transactionService;
	
	@Autowired
	private IAccountService accountService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable Long userId) {
	    Account account = accountService.getAccountByUserId(userId)
	            .orElseThrow(() -> new RuntimeException("Hesap bulunamadı"));

	    List<TransactionDTO> dtoList = transactionService.getTransactionsByAccount(account)
	            .stream()
	            .map(TransactionMapper::toDTO)
	            .toList();

	    return ResponseEntity.ok(dtoList);
	}
}
