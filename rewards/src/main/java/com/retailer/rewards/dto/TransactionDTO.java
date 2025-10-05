package com.retailer.rewards.dto;

import lombok.Data;

@Data
public class TransactionDTO {

	private int custId;
	private double amount;
	private String creationDate;

}
