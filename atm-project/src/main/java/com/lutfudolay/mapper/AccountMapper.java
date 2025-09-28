package com.lutfudolay.mapper;

import com.lutfudolay.dto.AccountDTO;
import com.lutfudolay.entities.Account;

public class AccountMapper {

	public static AccountDTO toDTO(Account account) {
        return new AccountDTO(
            account.getId(),
            account.getBalance(),
            account.getUser().getId()
        );
    }
}
