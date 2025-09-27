package com.lutfudolay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lutfudolay.service.IAccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/{userId}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Long userId, @RequestParam Double amount) {
        
		accountService.deposit(userId, amount);
        return ResponseEntity.ok(amount + " TL yatırıldı");
    }

    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long userId, @RequestParam Double amount) {
       
    	accountService.withdraw(userId, amount);
        return ResponseEntity.ok(amount + " TL çekildi");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromUserId, @RequestParam Long toUserId, @RequestParam Double amount) {
       
    	accountService.transfer(fromUserId, toUserId, amount);
        return ResponseEntity.ok(amount + " TL transfer edildi");
    }
}
