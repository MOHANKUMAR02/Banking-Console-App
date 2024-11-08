package com.project.bank_app.Main;

import java.util.*;

import com.project.bank_app.Entity.User;
import com.project.bank_app.Services.UserServices;
public class Main {

	private static Scanner scan = new Scanner(System.in);

	private static UserServices userServices = new UserServices();


	public static void main(String[] args) {

		Main main = new Main();

		while(true) {

			try {
				System.out.println("Enter your User Id");
				String userId = scan.next();

				System.out.println("Enter your Password");
				String password = scan.next();

				User user= userServices.loginCheck(userId, password);

				if(user!=null && user.getRole().equals("admin")){ 

					main.initadmin();		
				}
				else if(user!=null && user.getRole().equals("user")) {

					main.initCustomer(user);
				}
				else {

					System.out.println("Please check your Credentials.....");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Please Check Your Input, Enter the valid data.....");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void initadmin()
	{
		String user = "";
		boolean flag = true;

		while(flag) {

			try
			{

				System.out.println("1. Create User Account");
				System.out.println("2. Exit/logout");
				System.out.println("3. View All Transation");
				System.out.println("4. Check User Account Balance");
				System.out.println("5. Approve Check Book Request");
				System.out.println("Enter Your option");

				int enteredOption = scan.nextInt();

				switch(enteredOption) {


				case 1: createNewUserAccount();

				System.out.println();
				System.out.println("--------------- Task Completed ---------------------");

				break;

				case 2: flag = false;
				System.out.println("\"thank you for using our service...\" Successfully logged out.....");

				System.out.println();
				System.out.println("--------------- Task Completed ---------------------");

				break;

				case 3: System.out.println("Enter the User Id");
				user = scan.next();
				viewAllTransation(user);
				System.out.println();
				System.out.println("--------------- Request Successful ---------------------");
				break;

				case 4 : System.out.println("Enter user Id");
				user = scan.next();
				Double balance = checkAccountBalance(user);

				System.out.println("Account balance is : "+ balance);
				System.out.println();
				System.out.println("--------------- Request Successful ---------------------");
				break;

				case 5: List<String> userIds = getAllUsersCheckBookRequest();

				System.out.println("please Select below user");
				System.out.println(userIds);

				user = scan.next();

				approveCheckBookRequest(user);
				System.out.println("Approved Successfully");
				System.out.println();
				System.out.println("--------------- Request Successful ---------------------");
				break;


				default: System.out.println("Please enter valid option.....");
				break;
				}

			}
			catch (InputMismatchException e) {
				System.out.println("Please Check Your Input, Enter the valid data.....");
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void initCustomer(User user)
	{
		boolean flag = true;

		while(flag) {

			try
			{
				System.out.println("1. Exit/logout");
				System.out.println("2. Check Account Balance");
				System.out.println("3. Fund Transfer ");
				System.out.println("4. See All Transation");
				System.out.println("5. Raise Check Book Request");

				int selectedOption = scan.nextInt();

				switch(selectedOption)
				{

				case 1: flag = false;

				System.out.println("\"Thank you for using our service\" Successfully logged out.....");
				System.out.println();
				System.out.println("--------------- Request Successful ---------------------");
				break;

				case 2: Double balance = checkAccountBalance(user.getUserId());

				System.out.println("Your Account balance is : "+ balance);

				System.out.println();
				System.out.println("--------------- Request Successful ---------------------");

				break;

				case 3:  boolean Checkflag = true;

				while(Checkflag)
				{
					System.out.println("Enter User Id");
					String payee = scan.next();

					if(user.getUserId().equals(payee))
					{
						System.out.println("Please check both the Your Id -- "+ user.getUserId() + " and Receiver Id-- " + payee+ ", are same...");
						break;
					}
					else
					{
						User userVerfication = getUser(payee);

						if(userVerfication != null){

							Checkflag = false;

							System.out.println("Enter the Amount");
							double amount = scan.nextDouble();

							double Accountbalance = user.getAccountBalance();

							if( Accountbalance>= amount){

								transferAmount(user.getUserId(),payee,amount);
							}
							else {

								System.out.println("Insufficient Balance : "+ Accountbalance);
							}
						}
						else {

							System.out.println("Please Enter Valid User Id.....");
						}
					}
				}
				System.out.println();
				System.out.println("--------------- Request Successful ---------------------");
				break;

				case 4: viewAllTransation(user.getUserId());
				System.out.println();
				System.out.println("--------------- Request Successful ---------------------");
				break;

				case 5 : 
					String userId = user.getUserId();
					Map< String, Boolean> map = getAllCheckBookRequest();

					if(map.containsKey(userId) && map.get(userId))
					{
						System.out.println("You had raised an request already and successfully Approved.....");
					}
					else if(map.containsKey(userId) && !map.get(userId))
					{
						System.out.println("You had raised an request already and pending for Approval.....");
					}
					else 
					{	
						raiseCheckBookRequest(userId);
						System.out.println("Request Raised successfully.....");
					}
					System.out.println();
					System.out.println("--------------- Request Successful ---------------------");
					break;

				default: System.out.println("Please enter valid option.....");
				break;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Please Check Your Input, Enter the valid data.....");
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void createNewUserAccount()
	{
		try
		{
			boolean flag = true;
			System.out.println("Enter your Id");
			String userId = scan.next();

			if(getUser(userId)!=null)
			{
				System.out.println(" \"user is already Present.\" Creation Failed..... ");
			}
			else
			{
				scan.nextLine();
				System.out.println("Enter your Name");
				String name = scan.nextLine();

				System.out.println("Enter your Contact Number");
				long contact = scan.nextLong();

				System.out.println("Enter your Password");
				String password = scan.next();

				scan.nextLine();
				System.out.println("Enter your Address");
				String address = scan.nextLine();

				Boolean result = userServices.createNewUserAccount(userId, name, password, contact, address);

				if(result){

					System.out.println("New User Created Successfully...");
				}
			}

		}
		catch (InputMismatchException e) {
			System.out.println("Please Check Your Input, Enter the valid data.....");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Double checkAccountBalance(String userId)
	{
		return userServices.checkAccountBalance(userId);
	}

	private User getUser(String userId)
	{
		return userServices.getUser(userId);
	}

	private void transferAmount(String userId, String payeeId, double amount)
	{
		boolean result = userServices.transferAmount(userId, payeeId, amount);

		if(result){
			System.out.println("Amount Transfer Successful.....");
		}
		else{
			System.out.println("Amount Transfer Failed.....");
		}
	}

	private void viewAllTransation(String userId)
	{
		userServices.viewAllTransation(userId);
	}

	private void raiseCheckBookRequest(String userId)
	{
		userServices.raiseCheckBookRequest(userId);
	}

	private Map< String, Boolean> getAllCheckBookRequest()
	{
		return userServices.getAllCheckBookRequest();
	}

	private List<String> getAllUsersCheckBookRequest()
	{
		return userServices.getAllUsersCheckBookRequest();
	}

	private void approveCheckBookRequest(String userId)
	{
		userServices.approveCheckBookRequest(userId);
	}
}

