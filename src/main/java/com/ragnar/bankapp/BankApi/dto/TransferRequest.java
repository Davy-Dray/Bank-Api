package com.ragnar.bankapp.BankApi.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TransferRequest {

	String senderId;
	String reciverId;
	Float amount;
}
