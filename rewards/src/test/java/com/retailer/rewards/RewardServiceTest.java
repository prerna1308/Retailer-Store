package com.retailer.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.retailer.rewards.dto.CustomerDTO;
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
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustId(101);
		customerDTO.setCustName("ABCD");
		customerDTO.setStartDate("15/06/2025");
		customerDTO.setEndDate("06/08/2025");
		List<TransactionDTO> transDTOList = new ArrayList<TransactionDTO>();

		TransactionDTO tx1 = new TransactionDTO();
		tx1.setAmount(120.0);
		tx1.setCreationDate("13/08/2025");

		TransactionDTO tx2 = new TransactionDTO();
		tx2.setAmount(120.0);
		tx2.setCreationDate("23/08/2025");

		transDTOList.add(tx1);
		transDTOList.add(tx2);
		customerDTO.setTransactions(transDTOList);

		List<CustomerReward> rewards = rewardService.calculateReward(List.of(customerDTO));
		assertEquals(1, rewards.size());
		assertEquals(101, rewards.get(0).getCustId());
	}

}
