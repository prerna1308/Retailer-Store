package com.retailer.rewards.helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.retailer.rewards.entity.Transaction;

public class RewardsHelper {

	public static List<Transaction> getTransactionList() {
		List<Transaction> transList = new ArrayList<Transaction>();
		transList.add(new Transaction(123, 78, LocalDateTime.now()));
		transList.add(new Transaction(103, 314, LocalDateTime.now()));
		transList.add(new Transaction(234, 78, LocalDateTime.now()));
		transList.add(new Transaction(123, 74, LocalDateTime.parse("2025-08-19T10:27:27.267357787")));
		transList.add(new Transaction(123, 214, LocalDateTime.parse("2025-08-19T10:27:27.267357787")));
		transList.add(new Transaction(123, 180, LocalDateTime.parse("2025-07-19T10:27:27.267357787")));
		transList.add(new Transaction(123, 138, LocalDateTime.parse("2025-08-19T10:27:27.267357787")));
		transList.add(new Transaction(116, 125, LocalDateTime.parse("2025-08-19T10:27:27.267357787")));
		transList.add(new Transaction(103, 130, LocalDateTime.parse("2025-08-19T10:27:27.267357787")));
		transList.add(new Transaction(116, 98, LocalDateTime.parse("2025-08-19T10:27:27.267357787")));
		transList.add(new Transaction(116, 200, LocalDateTime.parse("2025-08-19T10:27:27.267357787")));
		transList.add(new Transaction(113, 400, LocalDateTime.parse("2025-08-19T10:27:27.267357787")));
		return transList;
	}

	public static int calculatePointValue(int reward) {
		int point = 0;
		if (reward > 100) {
			point += (reward - 100) * 2;
			point += 50;
		} else if (reward > 50)
			point += (reward - 50);
		return point;
	}

}
