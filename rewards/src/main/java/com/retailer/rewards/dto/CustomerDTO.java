package com.retailer.rewards.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDTO {

	@Valid
	@NotNull(message="Customer id is required")
	private int custId;
	
	@Valid
	private String custName;
	
	@Valid
	@NotNull(message="Start Date is required")
	private String startDate;
	
	@Valid
	@NotNull(message="End Date is required")
	private String endDate;
	
	@Valid
	@NotNull(message="Transaction list is required")
	private List<TransactionDTO> transactions;

}
