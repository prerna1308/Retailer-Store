package com.retailer.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.retailer.rewards.dto.TransactionDTO;
import com.retailer.rewards.entity.CustomerReward;
import com.retailer.rewards.service.RewardServiceImpl;

public class RewardServiceTest {

	private final RewardServiceImpl rewardService = new RewardServiceImpl();

	/**
	 * Method to test the response parameters of data returned from
	 * RewardServiceImpl
	 */
	@Test
	void testCalculateRewardPoints() {
		TransactionDTO tx = new TransactionDTO();
		tx.setCustId(101);
		tx.setAmount(120.0);
		tx.setCreationDate("13/08/2025 13:09");
		List<CustomerReward> rewards = rewardService.calculateReward(List.of(tx));
		assertEquals(1, rewards.size());
		assertEquals(101, rewards.get(0).getCustId());
	}

}
