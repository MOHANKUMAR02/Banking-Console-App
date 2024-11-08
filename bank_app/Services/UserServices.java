package com.project.bank_app.Services;

import java.util.List;
import java.util.Map;

import com.project.bank_app.Entity.User;
import com.project.bank_app.Repository.UserRepository;

public class UserServices {

	private UserRepository userRepository = new UserRepository();

	public void printUsers()
	{
		userRepository.displayUsersdetails();
	}

	public User loginCheck(String userName, String password)
	{
		return userRepository.login(userName,password);
	}

	public boolean createNewUserAccount(String userId, String name, String password, long contact, String address)
	{
		return userRepository.createNewUserAccount(userId, name, password, contact, address);
	}

	public Double checkAccountBalance(String userId)
	{
		return userRepository.checkAccountBalance(userId);
	}

	public User getUser(String userId)
	{
		return userRepository.getUser(userId);
	}

	public boolean transferAmount(String userId, String payeeId, double amount)
	{
		return userRepository.transferAmount(userId, payeeId, amount);
	}
	
	public void viewAllTransation(String userId)
	{
	  userRepository.viewAllTransation(userId);	
	}
	
	public void raiseCheckBookRequest(String userId)
	{
		userRepository.raiseCheckBookRequest(userId);
	}
	
	public Map< String, Boolean> getAllCheckBookRequest()
	{
		return userRepository.getAllCheckBookRequest();
	}

	public List<String> getAllUsersCheckBookRequest()
	{
		return userRepository.getAllUsersCheckBookRequest();
	}

	public void approveCheckBookRequest(String userId)
	{
		userRepository.approveCheckBookRequest(userId);
	}



}
