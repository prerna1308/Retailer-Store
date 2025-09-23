package com.retailer.rewards.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	private int custId;
	private double amount;
	private LocalDateTime creationDate;

}
