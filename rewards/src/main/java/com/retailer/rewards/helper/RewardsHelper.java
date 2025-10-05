package com.retailer.rewards.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.retailer.rewards.dto.TransactionDTO;
import com.retailer.rewards.entity.Transaction;

public class RewardsHelper {

	public static int calculatePointValue(int reward) {
		int point = 0;
		if (reward > 100) {
			point += (reward - 100) * 2;
			point += 50;
		} else if (reward > 50)
			point += (reward - 50);
		return point;
	}

	public static LocalDateTime convertStringToLocalDateTime(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
		return dateTime;
	}

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

}
