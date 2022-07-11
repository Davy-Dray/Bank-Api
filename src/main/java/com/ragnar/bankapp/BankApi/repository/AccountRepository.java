package com.ragnar.bankapp.BankApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.ragnar.bankapp.BankApi.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByAccountNumber(String accountNumber);

}
