package com.ragnar.bankapp.BankApi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private String type;
	private LocalDateTime timestamp;
	private float amount;
	private float balance;

	@ManyToOne
	@JsonIgnore
	private Account fromAccount;

	public Transaction(String type, float amount, float balance, Account fromAccount) {
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.fromAccount = fromAccount;
		this.timestamp = LocalDateTime.now();
	}

}
