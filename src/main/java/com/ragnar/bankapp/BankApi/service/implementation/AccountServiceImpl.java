package com.ragnar.bankapp.BankApi.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.ragnar.bankapp.BankApi.enums.AccountType;
import com.ragnar.bankapp.BankApi.exception.AccountNotFound;
import com.ragnar.bankapp.BankApi.exception.CustomerNotFoundExcepion;
import com.ragnar.bankapp.BankApi.model.Account;
import com.ragnar.bankapp.BankApi.model.Customer;
import com.ragnar.bankapp.BankApi.model.Transaction;
import com.ragnar.bankapp.BankApi.repository.AccountRepository;
import com.ragnar.bankapp.BankApi.repository.CustomerRepository;
import com.ragnar.bankapp.BankApi.repository.TransactionRepository;
import com.ragnar.bankapp.BankApi.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	CustomerRepository customerService;

	AccountRepository accountRepository;

	final TransactionRepository transactionRepository;

	final EmailService emailService;

	public AccountServiceImpl(CustomerRepository customerService, AccountRepository accountRepository,
			TransactionRepository transactionRepository, EmailService emailService) {
		this.customerService = customerService;
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.emailService = emailService;
	}

	@Override
	public Account createAccount(Long id) throws CustomerNotFoundExcepion, AddressException, MessagingException {

		Customer customer = customerService.findById(id).orElseThrow();

		Account newAccount = new Account();

		newAccount.setAccountHolder(customer);
		newAccount.setAccountType(AccountType.SAVINGS);
		newAccount.setCurrentbalance(1000);
		newAccount.setActive(true);
		newAccount.setAccountNumber(generateID());
		newAccount.setDateCreated(LocalDateTime.now());

		accountRepository.save(newAccount);

		System.out.println("account " + newAccount.getAccountNumber());
		// emailService.sendMail(customer.getFirstName(), newAccount.getAccoutNumber(),
		// customer.getEmail());

		return newAccount;

	}

	private String generateID() {

		return RandomStringUtils.randomNumeric(10);
	}

	private void createTransactionHistory(String typeOfTransaction, float amount, float postBalance,
			Account associatedAccount) {
		Transaction transaction = new Transaction(typeOfTransaction, amount, postBalance, associatedAccount);

		transactionRepository.save(transaction);
	}

	@Override
	public Float getAccountBalanceForCustomer(String accountId) throws AccountNotFound {

		Account account = checkAccount(accountId);

		return account.getCurrentbalance();
	}

	private Account checkAccount(String accountNumber) throws AccountNotFound {

		Account account = accountRepository.findByAccountNumber(accountNumber);

		if (account == null)
			throw new AccountNotFound("Account not found");

		return account;

	}

	public Account withdraw(String accountNumber, float amount) throws AccountNotFound {

		Account account = checkAccount(accountNumber);

		if (account.getCurrentbalance() < amount)
			throw new RuntimeException("Insufficient funds. Please try a lower amount.");

		account.setCurrentbalance(account.getCurrentbalance() - amount);

		accountRepository.save(account);

		createTransactionHistory("WITHDRAW", amount, account.getCurrentbalance(), account);

		return account;

	}

	public Account deposit(String accountNumber, float amount) throws AccountNotFound {

		Account account = checkAccount(accountNumber);

		account.setCurrentbalance(account.getCurrentbalance() + amount);

		accountRepository.save(account);

		createTransactionHistory("DEPOSIT", amount, account.getCurrentbalance(), account);

		return account;

	}

	public void transfer(String sender, String reciver, float theAmount) throws AccountNotFound {
		verifyer(sender, reciver, theAmount);
		withdraw(sender, theAmount);
		deposit(reciver, theAmount);

	}

	public List<Transaction> getStatementOfAccount(String accountId) throws AccountNotFound {

		Account account = checkAccount(accountId);

		List<Transaction> bankTransactions = account.getTransactions();

		bankTransactions.sort(

				// sort by transaction date..latest dates first
				(Transaction t1, Transaction t2) ->

				t2.getTimestamp().compareTo(t1.getTimestamp())

		);
		return bankTransactions;

	}

	private void verifyer(String accountcode1, String accountcode2, float theAmount) {
		if (accountcode1.equals(accountcode2)) {
			throw new RuntimeException("Cannot transfer into the same account.");
		} else if (String.class.isInstance(theAmount)) {
			throw new RuntimeException("Please enter an interger");
		}
	}

}
