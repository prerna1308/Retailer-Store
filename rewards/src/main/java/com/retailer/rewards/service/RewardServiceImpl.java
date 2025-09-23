package com.retailer.rewards.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.retailer.rewards.entity.CustomerReward;
import com.retailer.rewards.entity.Transaction;
import com.retailer.rewards.helper.RewardsHelper;

@Service
public class RewardServiceImpl implements RewardService {

	@Override
	public List<CustomerReward> calculateReward() {
		Map<Integer, Map<String, Integer>> custResponseMap = new ConcurrentHashMap<Integer, Map<String, Integer>>();
		for (Transaction trans : RewardsHelper.getTransactionList()) {
			int keyVal = trans.getCustId();
			Map<String, Integer> rewardMap = new ConcurrentHashMap<String, Integer>();
			if (custResponseMap.containsKey(keyVal)) {
				rewardMap = getRewardPoint((int) trans.getAmount(), trans.getCreationDate(),
						custResponseMap.get(trans.getCustId()));
			} else {
				rewardMap = getRewardPoint((int) trans.getAmount(), trans.getCreationDate(), null);
			}
			custResponseMap.put(keyVal, rewardMap);
		}
		return createCustRewardResponse(custResponseMap);
	}

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

	private Map<String, Integer> getRewardPoint(int amount, LocalDateTime localDateTime,
			Map<String, Integer> custMapVal) {
		String month = String.valueOf(localDateTime.getMonth());
		if (custMapVal != null) {
			int pointVal = 0;
			if (custMapVal.containsKey(String.valueOf(localDateTime.getMonth()))) {
				pointVal = custMapVal.get(month) + RewardsHelper.calculatePointValue(amount);
			} else {
				pointVal = RewardsHelper.calculatePointValue(amount);
			}
			custMapVal.put(month, pointVal);
			System.out.println("custMapVal: " + custMapVal);
			return custMapVal;
		} else {
			Map<String, Integer> rewardMap = new HashMap<String, Integer>();
			rewardMap.put(month, RewardsHelper.calculatePointValue(amount));
			System.out.println("rewardMap: " + rewardMap);
			return rewardMap;
		}
	}

}
