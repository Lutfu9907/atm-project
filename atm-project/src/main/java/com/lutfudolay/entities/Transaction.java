package com.lutfudolay.entities;

import java.time.LocalDateTime;

import com.lutfudolay.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//İşlem tipi(yatırma,çekme vb.)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TransactionType type;

	// İşlem tutarı
	@Column(nullable = false)
	private Double amount;

	// İşlem tarihi
	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	// İşlemin hangi hesaba ait olduğu
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
}
