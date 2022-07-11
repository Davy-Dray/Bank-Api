package com.ragnar.bankapp.BankApi.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ragnar.bankapp.BankApi.enums.AccountType;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private LocalDateTime dateCreated;

	private boolean isActive;

	private String accountNumber;

	@ManyToOne
	@JsonIgnore
	private Customer accountHolder;

	private float currentbalance;

	@OneToMany
	private List<Transaction> transactions;

	public Account(LocalDateTime dateCreated) {
		this.dateCreated = LocalDateTime.now();
	}

	
}
