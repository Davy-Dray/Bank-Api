package com.ragnar.bankapp.BankApi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ragnar.bankapp.BankApi.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	@Query("SELECT t FROM Transaction t WHERE t.transactionId=:transactionId")
	Iterable<Transaction> findBankTransactionById(@Param("transactionId") Integer transactionId);

}
