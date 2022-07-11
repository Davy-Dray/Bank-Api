package com.ragnar.bankapp.BankApi.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragnar.bankapp.BankApi.dto.TransactionRequest;
import com.ragnar.bankapp.BankApi.dto.TransferRequest;
import com.ragnar.bankapp.BankApi.exception.AccountNotFound;
import com.ragnar.bankapp.BankApi.exception.CustomerNotFoundExcepion;
import com.ragnar.bankapp.BankApi.exception.InsufficientFundsException;
import com.ragnar.bankapp.BankApi.exception.TransferRequestException;
import com.ragnar.bankapp.BankApi.model.Account;
import com.ragnar.bankapp.BankApi.model.Transaction;
import com.ragnar.bankapp.BankApi.service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/create/{id}")
	public Account createAccount(@PathVariable Long id)
			throws CustomerNotFoundExcepion, AddressException, MessagingException {

		return accountService.createAccount(id);
	}

	@PostMapping("/withdraw")
	public Account withdrawFromAccount(@RequestBody TransactionRequest request)
			throws InsufficientFundsException, CustomerNotFoundExcepion, AccountNotFound {

		return accountService.withdraw(request.getAccountId(), request.getAmount());
	}

	@PostMapping("/deposit")
	public Account depositToAccount(@RequestBody TransactionRequest request)
			throws InsufficientFundsException, CustomerNotFoundExcepion, AccountNotFound {

		return accountService.deposit(request.getAccountId(), request.getAmount());
	}

	@PostMapping("/transfer")
	public void transfer(@RequestBody TransferRequest request)
			throws CustomerNotFoundExcepion, AccountNotFound, InsufficientFundsException, TransferRequestException {

		accountService.transfer(request.getSenderId(), request.getReciverId(), request.getAmount());

	}

	@GetMapping("/balance/{accountId}")
	public Float getAccountBalance(@PathVariable String accountId) throws CustomerNotFoundExcepion, AccountNotFound {

		return accountService.getAccountBalanceForCustomer(accountId);
	}

	@GetMapping("/statement/{accountId}")
	public List<Transaction> getStatement(@PathVariable String accountId)
			throws CustomerNotFoundExcepion, AccountNotFound {

		return accountService.getStatementOfAccount(accountId);
	}

}
