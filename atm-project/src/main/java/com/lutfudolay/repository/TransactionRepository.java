package com.lutfudolay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lutfudolay.entities.Account;
import com.lutfudolay.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByAccount(Account account);
}
