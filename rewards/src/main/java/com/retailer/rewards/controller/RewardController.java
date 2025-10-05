package com.retailer.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.rewards.dto.TransactionDTO;
import com.retailer.rewards.entity.CustomerReward;
import com.retailer.rewards.service.RewardService;

@RestController
public class RewardController {

	@Autowired
	private RewardService rewardService;

	@GetMapping("/calculateReward")
	private ResponseEntity<List<CustomerReward>> calculateReward(@RequestBody List<TransactionDTO> transList) {
		return new ResponseEntity<List<CustomerReward>>(rewardService.calculateReward(transList), HttpStatus.OK);
	}

}
