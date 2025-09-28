package com.lutfudolay.service;

import java.util.List;

import com.lutfudolay.entities.Account;
import com.lutfudolay.entities.Transaction;
import com.lutfudolay.enums.TransactionType;

public interface ITransactionService {
	
	List<Transaction> getTransactionsByAccount(Account account);
    Transaction saveTransaction(Transaction transaction);
	void logTransaction(Long userId, Double amount, TransactionType deposit);
}
