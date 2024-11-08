package com.project.bank_app.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.project.bank_app.Entity.Transaction;
import com.project.bank_app.Entity.User;

public class UserRepository {

	private static  Set<User> users = new HashSet<>();
	private static List<Transaction> transactions = new ArrayList<Transaction>();
	private Map< String, Boolean> checkBookRequest = new HashMap<>();


	static {

		User admin = new User("admin123","Admin",001,"admin123",9876543212l,"admin",0.0,"Krishangiri");
		User user1 = new User("user1","Mohan", 002, "user1",785645231 ,"user", 2000.0, "Dharamapuri");
		User user2 = new User("user2","kumar",003,"user2",90876543l,"user",1000.0,"vadalore");
		User user3 = new User("user3","kumar",004,"user3",90876547l,"user",3000.0,"vadalore");
		users.add(admin);
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}

	public void displayUsersdetails()
	{
		System.out.println(users);
	}

	public User login(String userId, String password)
	{
		List<User> finalList = users.stream().filter( user -> user.getUserId().equals(userId) && user.getPassword().equals(password))
				.collect(Collectors.toList());

		if(!finalList.isEmpty()){
			return finalList.get(0);
		}
		else{
			return null;
		}
	}

	public boolean createNewUserAccount(String userId, String name, String password, long contact, String address)
	{
		List<User> CheckList = users.stream().filter(user -> user.getUserId().equals(userId)).collect(Collectors.toList());

		if(CheckList.isEmpty()) {

			User user = new User(userId,name,004,password,contact,"user",500.00,address);

			return users.add(user);
		}
		else {

			return false;
		}

	}

	public Double checkAccountBalance(String userId)
	{
		List<User> result = users.stream().filter(user -> user.getUserId().equals(userId)).collect(Collectors.toList());

		if(!result.isEmpty()) {

			return result.get(0).getAccountBalance();	
		} 
		else {

			return null;
		}
	}

	public User getUser(String userId)
	{
		List<User> result = users.stream().filter(user -> user.getUserId().equals(userId)).collect(Collectors.toList());

		if(!result.isEmpty()) {

			return result.get(0);
		} 
		else {

			return null;
		}


	}

	public boolean transferAmount(String userId, String payeeId, double amount)
	{
		boolean isDebit = debit(userId, amount, payeeId);

		boolean isCredit = credit(payeeId, amount,userId);

		return isDebit && isCredit;
	}

	public boolean debit(String userId, double amount, String payeeId)
	{
		User user = getUser(userId);

		users.remove(user);

		double initialBalance = user.getAccountBalance() ;
		double finalBalance = initialBalance - amount;

		user.setAccountBalance(finalBalance);

		Transaction transaction = new Transaction( 
				LocalDate.now() ,
				payeeId,
				amount,
				"debit",
				initialBalance,
				finalBalance,
				userId
				);

		transactions.add(transaction);
		return users.add(user); 
	}

	public boolean credit(String payeeId, double amount,String userId)
	{

		User user = getUser(payeeId);

		users.remove(user);

		double initialBalance = user.getAccountBalance();

		double finalBalance = initialBalance + amount;

		user.setAccountBalance(finalBalance);

		Transaction transaction = new Transaction( 
				LocalDate.now() ,
				userId,
				amount,
				"credit",
				initialBalance,
				finalBalance,
				payeeId
				);

		transactions.add(transaction);

		return users.add(user);
	}

	public void viewAllTransation(String userId)
	{
		List<Transaction> result = transactions.stream().filter(user -> user.getTransactionPerformedBy().equals(userId)).collect(Collectors.toList());

		String outerFormate = "%-15s %-10s %-15s %-10s %-20s %-10s";
		String innerFormate = "%-15s %-10s %-15f %-10s %-20f %-10f \n";

		System.out.printf(outerFormate,"Date", "UserId", "Amount", "Type", "Initial Balance", "finalBanlance");
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------");
		for(Transaction trans : result)
		{
			System.out.printf(innerFormate,trans.getTransactionDate(),trans.getTransferToUserId(),trans.getTransationAmount(),trans.getTransationType(),trans.getInitialBalance(), trans.getFinalBalance());
		}
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------1");
	}

	public void raiseCheckBookRequest(String userId)
	{
		checkBookRequest.put(userId, false);
	}

	public Map< String, Boolean> getAllCheckBookRequest()
	{
		return checkBookRequest;
	}

	public List<String> getAllUsersCheckBookRequest()
	{
		ArrayList<String> userIds = new ArrayList<>();

		for( Map.Entry<String, Boolean> entry : checkBookRequest.entrySet())
		{
			if(!entry.getValue())
			{
				userIds.add(entry.getKey());
			}
		}

		return userIds;
	}

	public void approveCheckBookRequest(String userId)
	{
		if(checkBookRequest.containsKey(userId))

			checkBookRequest.put(userId, true);

	}



}
