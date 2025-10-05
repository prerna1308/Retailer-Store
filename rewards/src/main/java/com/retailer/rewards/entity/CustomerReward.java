package com.retailer.rewards.entity;

import java.util.Map;

import lombok.Data;

@Data
public class CustomerReward {

	private int custId;
	private Map<String, Integer> rewardPoints;
	private int totalPoints;

}
