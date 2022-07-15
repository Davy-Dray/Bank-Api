package com.ragnar.bankapp.BankApi.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.ragnar.bankapp.BankApi.exception.AccountNotFound;
import com.ragnar.bankapp.BankApi.exception.CustomerNotFoundExcepion;
import com.ragnar.bankapp.BankApi.exception.InsufficientFundsException;
import com.ragnar.bankapp.BankApi.model.Account;
import com.ragnar.bankapp.BankApi.model.Transaction;

public interface AccountService {

	public Float getAccountBalanceForCustomer(String accountId) throws AccountNotFound;

	Account createAccount(Long id) throws CustomerNotFoundExcepion, AddressException, MessagingException;

	public Account withdraw(String accountNumber, float amount) throws AccountNotFound, InsufficientFundsException;

	public Account deposit(String accountNumber, float amount) throws AccountNotFound;
	
	public void transfer(String senderId, String recieverId, float theAmount) throws AccountNotFound, InsufficientFundsException ;

	public List<Transaction> getStatementOfAccount(String accountId) throws AccountNotFound;

}
