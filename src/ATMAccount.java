// File: ATMAccount.java
// Created: 3/25/2019
// Last Change: 3/25/2019 
// Author: Brett Smith
//
// Account object
// 



import java.text.DecimalFormat;
import java.util.Scanner;

public class ATMAccount // implements ATMInterface 
{
	private double bal;
	private double input;

	private DecimalFormat df2;
	
	public Scanner kbd;

	public ATMAccount() 
	{
		bal = 1000.00;
		input = -1;
		df2 = new DecimalFormat("$0.00");
		kbd = new Scanner(System.in);
	}
	
	private void Withdrawal()
	{
		
		int select = -1, fee=25;
				
		System.out.print("How much do you want to withdraw? ");
		input = kbd.nextDouble();
		
		if(input%20 != 0) {
			System.out.println("Invalid Entry. Please enter denomination of $20");
			Withdrawal();
		}
		else if(input <= 0) {
			System.out.println("Invalid Entry. Please enter a value greater than 0");
			Withdrawal();
		}
		else if(bal <=0) {
			System.out.println("You currently have zero or negative balance. Please deposit more funds.\n");
		}
		else if(input > bal) {
			System.out.println("Insufficient funds for withdrawal. Overdraw with a fee of $"+fee+"?");
			System.out.println("[1] Yes    [2]No"); // Assuming Only 1 or 2 are active buttons.
			select = kbd.nextInt();
			
			if(select == 1) {
				subtractAmount(input+fee);
			}
			else {
				Withdrawal();
			}
		}
	
		else {
			subtractAmount(input);
		}	
	}
	
	private void subtractAmount(double x)
	{
		bal -= x;
		System.out.println("Your new balance is: " + df2.format(bal)+"\n");
	}
		
	private void Deposit()
	{
	// When Deposit
		System.out.println("Please enter deposit amount");
		input = kbd.nextDouble();
		bal += input; 
		System.out.print("Your new balance is: " + df2.format(bal)+"\n");
		
	}
	private void CheckBalance()
	{
		// When Check Balance
		System.out.println("Your current balance is: " + df2.format(bal)+"\n");	
	}
	
	public void Menu()
	{
		int choice = -1;
		System.out.println("WELCOME TO SMITHBANK MENU");
		System.out.println(" 1: Withdraw\n 2: Deposit\n 3: Check Balance \n 4: Exit");
		
		choice = kbd.nextInt();
		
		switch(choice) {
		case 1:  // Withdrawal
			Withdrawal();
			break;
		case 2: // Deposit
			Deposit();
			break;
		case 3: // Check Balance
			CheckBalance();
			break;
		case 4: // Exit
			System.out.println("Thank you for using SmithBank. Goodbye");
			return;
		default: 
			System.out.println("Invalid option. Please select a valid task option");
			break;

		}
		Menu();
	}	
	
}