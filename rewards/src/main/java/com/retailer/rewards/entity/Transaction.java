package com.retailer.rewards.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Transaction {

	private double amount;
	private LocalDate creationDate;

}
