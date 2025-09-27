package com.lutfudolay.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lutfudolay.entities.Account;
import com.lutfudolay.entities.Transaction;
import com.lutfudolay.entities.User;
import com.lutfudolay.enums.TransactionType;
import com.lutfudolay.repository.AccountRepository;
import com.lutfudolay.repository.UserRepository;
import com.lutfudolay.service.IAccountService;
import com.lutfudolay.service.ITransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional //ya hep ya hiç mantığı -> işlem başarılı ise commit edilir,
			   //hata varsa rollback ile hiçbiri uygulanmaz. Tüm database işlemleri tek paket (transaction) çalışır.

public class AccountServiceImpl implements IAccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ITransactionService transactionService;
	
	@Override
	public Optional<Account> getAccountByUserId(Long userId) {
		return userRepository.findById(userId).map(User::getAccount);
	}

	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void deposit(Long userId, Double amount) {
		Account account = getAccountByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Hesap bulunamadı"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEPOSIT);
        transactionService.saveTransaction(transaction);
	}

	@Override
	public void withdraw(Long userId, Double amount) {
		Account account = getAccountByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Hesap bulunamadı"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Yetersiz bakiye!");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.WITHDRAW);
        transactionService.saveTransaction(transaction);
	}

	@Override
	public void transfer(Long fromUserId, Long toUserId, Double amount) {
		 withdraw(fromUserId, amount); // önce çek
	     deposit(toUserId, amount);    // sonra yatır
	     
	     Account senderAccount = getAccountByUserId(fromUserId)
	                .orElseThrow(() -> new RuntimeException("Hesap bulunamadı"));
	     Transaction transferLog = new Transaction();
	     transferLog.setAccount(senderAccount);
	     transferLog.setAmount(amount);
	     transferLog.setType(TransactionType.TRANSFER);
	     transactionService.saveTransaction(transferLog);
	}
}
