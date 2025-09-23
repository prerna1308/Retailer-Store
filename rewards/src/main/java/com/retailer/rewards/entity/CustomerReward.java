package com.retailer.rewards.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReward {

	private int custId;
	private Map<String, Integer> rewardPoints;
	private int totalPoints;

}
