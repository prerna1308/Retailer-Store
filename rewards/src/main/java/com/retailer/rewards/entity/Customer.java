package com.retailer.rewards.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Customer {

	private Integer custId;
	private String custName;
	private List<Transaction> transList;
	private LocalDate startDate;
	private LocalDate endDate;

}
