package com.lutfudolay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lutfudolay.entities.Account;
import com.lutfudolay.entities.Transaction;
import com.lutfudolay.enums.TransactionType;
import com.lutfudolay.repository.TransactionRepository;
import com.lutfudolay.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public List<Transaction> getTransactionsByAccount(Account account) {
		
		return transactionRepository.findByAccount(account);
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {

		return transactionRepository.save(transaction);
	}

	@Override
	public void logTransaction(Long userId, Double amount, TransactionType deposit) {
		// TODO Auto-generated method stub
		
	}

}
