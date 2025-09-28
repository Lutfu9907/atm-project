package com.lutfudolay.mapper;

import com.lutfudolay.dto.TransactionDTO;
import com.lutfudolay.entities.Transaction;

public class TransactionMapper {

	public static TransactionDTO toDTO(Transaction transaction) {
        return new TransactionDTO(
            transaction.getId(),
            transaction.getType(),
            transaction.getAmount(),
            transaction.getCreatedAt(),
            transaction.getAccount().getId()
        );
    }
}
