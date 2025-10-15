package com.retailer.rewards.service;

import java.util.List;

import com.retailer.rewards.dto.CustomerDTO;
import com.retailer.rewards.entity.CustomerReward;

public interface RewardService {

	/**
	 * This method calculates the Reward for each Customer individually over the
	 * last 3 months
	 * 
	 * @param transList
	 * @return CustomerReward list
	 */
	List<CustomerReward> calculateReward(List<CustomerDTO> custList);

}
