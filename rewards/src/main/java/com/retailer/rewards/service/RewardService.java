package com.retailer.rewards.service;

import java.util.List;

import com.retailer.rewards.dto.TransactionDTO;
import com.retailer.rewards.entity.CustomerReward;

public interface RewardService {

	List<CustomerReward> calculateReward(List<TransactionDTO> transList);

}
