package com.retailer.rewards.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Transaction {

	private int custId;
	private double amount;
	private LocalDateTime creationDate;

}
