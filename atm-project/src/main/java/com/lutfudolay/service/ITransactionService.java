package com.lutfudolay.service;

import java.util.List;

import com.lutfudolay.entities.Account;
import com.lutfudolay.entities.Transaction;

public interface ITransactionService {
	
	List<Transaction> getTransactionsByAccount(Account account);
    Transaction saveTransaction(Transaction transaction);
}
