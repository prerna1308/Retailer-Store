package com.retailer.rewards.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDTO {

	@Valid
	@NotNull(message = "Amount is required")
	private Double amount;

	@Valid
	@NotNull(message = "Date of creation is required")
	private String creationDate;

}
