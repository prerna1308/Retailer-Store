package com.retailer.rewards;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailer.rewards.controller.RewardController;
import com.retailer.rewards.dto.CustomerDTO;
import com.retailer.rewards.dto.TransactionDTO;
import com.retailer.rewards.entity.CustomerReward;
import com.retailer.rewards.service.RewardService;

@WebMvcTest(RewardController.class)
public class RewardsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@SuppressWarnings("removal")
	@MockBean
	private RewardService rewardService;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * This method provides valid request to test the fuctionality
	 * 
	 * @throws Exception
	 */
	@Test
	void testValidTransactionListReturnsRewards() throws Exception {
		// Given: valid request
		List<CustomerDTO> custDTOList = new ArrayList<CustomerDTO>();
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustId(101);
		customerDTO.setCustName("ABCD");
		customerDTO.setStartDate("15/06/2025");
		customerDTO.setEndDate("06/08/2025");
		List<TransactionDTO> transDTOList = new ArrayList<TransactionDTO>();

		TransactionDTO tx1 = new TransactionDTO();
		tx1.setAmount(120.0);
		tx1.setCreationDate("2025-09-01");

		transDTOList.add(tx1);
		customerDTO.setTransactions(transDTOList);

		custDTOList.add(customerDTO);

		CustomerReward reward = new CustomerReward();
		reward.setCustId(101);
		Map<String, Integer> rewardPoints = new HashMap<String, Integer>();
		rewardPoints.put("October", 123);
		rewardPoints.put("July", 13);
		rewardPoints.put("October", 89);
		rewardPoints.put("August", 45);
		rewardPoints.put("September", 21);
		reward.setRewardPoints(rewardPoints);

		when(rewardService.calculateReward(custDTOList)).thenReturn(List.of(reward));

		mockMvc.perform(post("/calculateReward").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(List.of(customerDTO)))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].customerId").value(101))
				.andExpect(jsonPath("$[0].totalRewardPoints").value(45));
	}

	/**
	 * This method provides creation date as null to test neative case
	 * 
	 * @throws Exception
	 */
	@Test
	void testInvalidTransactionListReturnsBadRequest() throws Exception {
		// Given: missing creationDate
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustId(45);
		customerDTO.setEndDate("13/09/2025");

		TransactionDTO tx = new TransactionDTO();
		tx.setAmount(100.0);
		tx.setCreationDate(null); // invalid

		customerDTO.setTransactions(List.of(tx));

		mockMvc.perform(post("/calculateReward").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(List.of(customerDTO)))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("creationDate")));
	}

}
