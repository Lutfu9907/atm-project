package com.lutfudolay.dto;

import java.time.LocalDateTime;

import com.lutfudolay.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

	private Long id;
    private TransactionType type;
    private Double amount;
    private LocalDateTime createdAt;
    private Long accountId;
}
