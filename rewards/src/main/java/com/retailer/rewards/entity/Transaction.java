package com.retailer.rewards.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class Transaction {

	private int custId;
	private double amount;
	private LocalDateTime creationDate;

}
