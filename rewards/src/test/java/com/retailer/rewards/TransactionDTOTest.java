package com.retailer.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.retailer.rewards.dto.TransactionDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class TransactionDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Method to test the request parameters validation 
	 */
	@Test
	void testMissingFieldsTriggerValidationErrors() {
		TransactionDTO tx = new TransactionDTO(); // Null value for all fields
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(tx);
		assertEquals(3, violations.size());
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Id is required")));
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Amount is required")));
		assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Date of creation is required")));
	}

}
