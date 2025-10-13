package com.retailer.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.rewards.dto.TransactionDTO;
import com.retailer.rewards.entity.CustomerReward;
import com.retailer.rewards.service.RewardService;

import jakarta.validation.Valid;

@RestController
public class RewardController {

	@Autowired
	private RewardService rewardService;

	/**
	 * This method calculates points earned by Customer for last three months from
	 * given Transaction list provided for each Customer
	 * 
	 * @param transList
	 * @return CustomerReward list
	 */
	@PostMapping("/calculateReward")
	private ResponseEntity<List<CustomerReward>> calculateReward(@Valid @RequestBody List<TransactionDTO> transList) {
		return new ResponseEntity<List<CustomerReward>>(rewardService.calculateReward(transList), HttpStatus.OK);
	}

}
