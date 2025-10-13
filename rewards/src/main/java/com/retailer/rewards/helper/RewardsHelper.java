package com.retailer.rewards.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.retailer.rewards.dto.TransactionDTO;
import com.retailer.rewards.entity.Transaction;

public class RewardsHelper {

	/**
	 * This method calculates the point earned based on Customer Rewards
	 * 
	 * @param reward
	 * @return points
	 */
	public static int calculatePointValue(int reward) {
		int point = 0;
		if (reward > 100) {
			point += (reward - 100) * 2;
			point += 50;
		} else if (reward > 50)
			point += (reward - 50);
		return point;
	}

	/**
	 * It converts the given date from String format to Java LocalDateTime format
	 * 
	 * @param dateStr
	 * @return dateTime
	 */
	public static LocalDateTime convertStringToLocalDateTime(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
		return dateTime;
	}

	/**
	 * It converts Transaction DTO object to Transaction DAO object
	 * 
	 * @param transDTOList
	 * @return transList
	 */
	public static List<Transaction> convertToTransObj(List<TransactionDTO> transDTOList) {
		List<Transaction> transList = new ArrayList<Transaction>();
		for (TransactionDTO transDTO : transDTOList) {
			Transaction transaction = new Transaction();
			transaction.setCustId(transDTO.getCustId());
			transaction.setAmount(transDTO.getAmount());
			transaction.setCreationDate(convertStringToLocalDateTime(transDTO.getCreationDate()));
			transList.add(transaction);
		}
		return transList;
	}

	/**
	 * It returns the list of last three months, calculated from current date
	 * 
	 * @param currentDate
	 * @return
	 */
	public static List<Month> getLastThreeMonthList(LocalDate currentDate) {
		List<Month> lastThreeMonth = new ArrayList<Month>();
		lastThreeMonth.add(currentDate.getMonth());
		lastThreeMonth.add(currentDate.minusMonths(1).getMonth());
		lastThreeMonth.add(currentDate.minusMonths(2).getMonth());
		return lastThreeMonth;
	}

}
