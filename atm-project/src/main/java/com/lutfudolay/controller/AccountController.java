package com.lutfudolay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lutfudolay.dto.AccountDTO;
import com.lutfudolay.entities.Account;
import com.lutfudolay.enums.TransactionType;
import com.lutfudolay.mapper.AccountMapper;
import com.lutfudolay.service.IAccountService;
import com.lutfudolay.service.ITransactionService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private ITransactionService transactionService;
	
	@GetMapping("/{userId}/balance")
	public ResponseEntity<AccountDTO> getBalance(@PathVariable Long userId) {
	    Account account = accountService.getAccountByUserId(userId)
	            .orElseThrow(() -> new RuntimeException("Hesap bulunamadı"));

	    return ResponseEntity.ok(AccountMapper.toDTO(account));
	}
	
	@PostMapping("/{userId}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Long userId, @RequestParam Double amount) {
        
		accountService.deposit(userId, amount);
		transactionService.logTransaction(userId, amount, TransactionType.DEPOSIT);
		
        return ResponseEntity.ok(amount + " TL yatırıldı");
    }

    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long userId, @RequestParam Double amount) {
       
    	accountService.withdraw(userId, amount);
    	transactionService.logTransaction(userId, amount, TransactionType.WITHDRAW);

        return ResponseEntity.ok(amount + " TL çekildi");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromUserId, @RequestParam Long toUserId, @RequestParam Double amount) {
       
    	accountService.transfer(fromUserId, toUserId, amount);
    	
    	transactionService.logTransaction(fromUserId, amount, TransactionType.TRANSFER_OUT);
        transactionService.logTransaction(toUserId, amount, TransactionType.TRANSFER_IN);
        
        return ResponseEntity.ok(amount + " TL transfer edildi");
    }
}
