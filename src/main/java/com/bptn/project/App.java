package com.bptn.project;

import java.util.Scanner;

import com.bptn.project.services.ExpenseService;

public class App {
	// Define color codes
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	
	public static void main(String[] args) {
		// Create an instance of the Expense service
		ExpenseService expenseService = new ExpenseService();
		
		// Import the scanner method
		Scanner scanner = new Scanner(System.in);
		
	
			System.out.println("\n" + GREEN + "Welcome to the expense tracker program." + RESET);
			System.out.println(RED + "Main Menu. Please choose among the following options: " + RESET);
			System.out.println("");
			System.out.println(RED + "1:" + RESET + GREEN + " Add a new expense" + RESET);
			System.out.println(RED + "2:" + RESET + GREEN +" View your expenses");
			System.out.println(RED + "3:" + RESET + GREEN +" Edit an expense");
			System.out.println(RED + "4:" + RESET + GREEN +" Delete new expense");
			System.out.println(RED + "5:" + RESET + GREEN +" Exit Main Menu");
			
			int selection = scanner.nextInt();
			
			switch(selection) {
			case 1:
				expenseService.addExpense(); // Call addExpense method
				break;
			case 2:
				System.out.println(expenseService.getAllExpenses());
				break;
			case 3:
				expenseService.editExpense();
				break;
			case 4:
				System.out.println("Enter the ID of the expense you want to delete");
				int idToDelete = scanner.nextInt();
				expenseService.deleteExpense(idToDelete);
				break;
			case 5:
				System.out.println("Exiting the App. Goodbye!");
				scanner.close();
				return; // Exit the loop and program
			default:
				System.out.println("Invalid selection. Please choose a valid menu option.");
			}
		

	}
}
