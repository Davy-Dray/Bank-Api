package com.ragnar.bankapp.BankApi.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TransactionRequest {

	String accountId;
	float amount;

}
