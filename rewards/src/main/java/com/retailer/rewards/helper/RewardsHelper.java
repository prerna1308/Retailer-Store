package com.retailer.rewards.helper;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.retailer.rewards.dto.CustomerDTO;
import com.retailer.rewards.dto.TransactionDTO;
import com.retailer.rewards.entity.Customer;
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
	public static LocalDate convertStringToLocalDateTime(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateVal = LocalDate.parse(dateStr, formatter);
		return dateVal;
	}

	/**
	 * It converts Transaction DTO object to Transaction DAO object
	 * 
	 * @param custDTOList
	 * @return transList
	 */
	public static List<Customer> convertToCustomerObj(List<CustomerDTO> custDTOList) {
		List<Customer> custList = new ArrayList<Customer>();
		for (CustomerDTO customerDTO : custDTOList) {
			Customer customer = new Customer();
			customer.setCustId(customerDTO.getCustId());
			customer.setStartDate(convertStringToLocalDateTime(customerDTO.getStartDate()));
			customer.setEndDate(convertStringToLocalDateTime(customerDTO.getEndDate()));
			customer.setCustName(customerDTO.getCustName());
			List<Transaction> transList = new ArrayList<Transaction>();
			for (TransactionDTO transDTO : customerDTO.getTransactions()) {
				Transaction transaction = new Transaction();
				transaction.setAmount(transDTO.getAmount());
				transaction.setCreationDate(convertStringToLocalDateTime(transDTO.getCreationDate()));
				transList.add(transaction);
			}
			customer.setTransList(transList);
			custList.add(customer);
		}
		return custList;
	}

	/**
	 * It checks if the given start date and end date fall in the consecutive three
	 * months
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkIfMonthsAreConsecutive(LocalDate startDate, LocalDate endDate) {
		YearMonth startMonth = YearMonth.from(startDate);
		YearMonth endMonth = YearMonth.from(endDate);
		long monthsBetween = ChronoUnit.MONTHS.between(startMonth, endMonth) + 1;
		boolean isThreeConsecutiveMonths = monthsBetween == 3;
		return isThreeConsecutiveMonths;
	}

}
