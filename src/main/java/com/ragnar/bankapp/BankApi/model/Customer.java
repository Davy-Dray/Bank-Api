package com.ragnar.bankapp.BankApi.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

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
public class Customer {

	@NotBlank(message = "firstname can't be null")
	private String firstName;

	@NotBlank(message = "lastName can't be null")
	private String lastName;

	@Column(unique = true)
	@NotBlank(message = "email can't be null")

	private String email;
	@NotBlank(message = "password can't be null")

	
	private String password;

	@OneToMany
	@JsonIgnore
	private Set<Account> customerAccounts;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long id;

}
