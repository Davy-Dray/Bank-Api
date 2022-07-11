package com.ragnar.bankapp.BankApi.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class CustomerUpdate {


	private String firstName;

	private String lastName;

	
	private String email;
}
