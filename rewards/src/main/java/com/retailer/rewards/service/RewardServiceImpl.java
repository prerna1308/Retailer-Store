package com.retailer.rewards.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.retailer.rewards.dto.CustomerDTO;
import com.retailer.rewards.entity.Customer;
import com.retailer.rewards.entity.CustomerReward;
import com.retailer.rewards.entity.Transaction;
import com.retailer.rewards.helper.RewardsHelper;

@Service
public class RewardServiceImpl implements RewardService {

	/**
	 * This method calculates the Reward for each Customer individually over the
	 * given 3 months
	 * 
	 * @param transList
	 * @return CustomerReward list
	 */
	@Override
	public List<CustomerReward> calculateReward(List<CustomerDTO> custDTOList) {
		Map<Integer, Map<String, Integer>> custResponseMap = new ConcurrentHashMap<Integer, Map<String, Integer>>();

		for (Customer customer : RewardsHelper.convertToCustomerObj(custDTOList)) {
			if (RewardsHelper.checkIfMonthsAreConsecutive(customer.getStartDate(), customer.getEndDate())) {
				for (Transaction trans : customer.getTransList()) {
					int keyVal = customer.getCustId();
					Map<String, Integer> rewardMap = new ConcurrentHashMap<String, Integer>();
					if (custResponseMap.containsKey(keyVal)) {
						rewardMap = getRewardPoint((int) trans.getAmount(), trans.getCreationDate(),
								custResponseMap.get(customer.getCustId()));
					} else {
						rewardMap = getRewardPoint((int) trans.getAmount(), trans.getCreationDate(), null);
					}
					custResponseMap.put(keyVal, rewardMap);
				}
			} else {
				System.out.println(
						"Customer " + customer.getCustName() + " start and end dates are not from consecutive 3 months.");
			}

		}
		return createCustRewardResponse(custResponseMap);
	}

	/**
	 * This method converts the given customer Response Map to a list for
	 * CustomerRewrd object list
	 * 
	 * @param custResponseMap
	 * @return CustomerReward list
	 */
	private List<CustomerReward> createCustRewardResponse(Map<Integer, Map<String, Integer>> custResponseMap) {
		List<CustomerReward> customerRewardList = new ArrayList<CustomerReward>();
		for (Map.Entry<Integer, Map<String, Integer>> entry : custResponseMap.entrySet()) {
			CustomerReward customerReward = new CustomerReward();
			customerReward.setCustId(entry.getKey());
			customerReward.setRewardPoints(entry.getValue());
			int totalPoints = 0;
			for (int point : entry.getValue().values()) {
				totalPoints += point;
			}
			customerReward.setTotalPoints(totalPoints);
			customerRewardList.add(customerReward);
		}
		return customerRewardList;
	}

	/**
	 * This method calculates the point for each month, mapped corresponding to
	 * their month
	 * 
	 * @param amount
	 * @param localDate
	 * @param custMapVal
	 * @return rewardsPointMap
	 */
	private Map<String, Integer> getRewardPoint(int amount, LocalDate localDate, Map<String, Integer> custMapVal) {
		String month = String.valueOf(localDate.getMonth());
		if (custMapVal != null) {
			int pointVal = 0;
			if (custMapVal.containsKey(String.valueOf(localDate.getMonth()))) {
				pointVal = custMapVal.get(month) + RewardsHelper.calculatePointValue(amount);
			} else {
				pointVal = RewardsHelper.calculatePointValue(amount);
			}
			custMapVal.put(month, pointVal);
			return custMapVal;
		} else {
			Map<String, Integer> rewardMap = new HashMap<String, Integer>();
			rewardMap.put(month, RewardsHelper.calculatePointValue(amount));
			return rewardMap;
		}
	}

}
