package com.bptn.project;

import java.security.InvalidAlgorithmParameterException;
import java.util.Scanner;

import com.bptn.project.services.ExpenseService;

public class App {
	// Define color codes
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String BLUE = "\u001B[34m";
	public static final String YELLOW = "\u001B[33m";

	public static void main(String[] args) {
		// Create an instance of the Expense service
		ExpenseService expenseService = new ExpenseService();

		// Import the scanner method
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n" + GREEN + "Welcome to the expense tracker program." + RESET);
			System.out.println(RED + "Main Menu. Please choose among the following options: " + RESET);
			System.out.println("");
			System.out.println(RED + "1:" + RESET + GREEN + " Add a new expense" + RESET);
			System.out.println(RED + "2:" + RESET + GREEN + " View your expense report");
			System.out.println(RED + "3:" + RESET + GREEN + " Edit an expense");
			System.out.println(RED + "4:" + RESET + GREEN + " Delete an expense");
			System.out.println(RED + "5:" + RESET + GREEN + " Download your expenses");
			System.out.println(RED + "6:" + RESET + GREEN + " Exit Main Menu");
			
			System.out.println("Select a service: ");

			int selection;
			try {
				selection = scanner.nextInt();
			} catch (Exception e) {
				System.out.println(RED + "Invalid input! Please enter a number between 1 and 6");
				scanner.next();
				continue;
			}

			switch (selection) {
			case 1:
				expenseService.addExpense(); // Call addExpense method
				break;
			case 2:
				expenseService.displayExpenses();
				break;
			case 3:
				expenseService.editExpense();
				break;
			case 4:
				System.out.println("Enter the ID of the expense you want to delete");
				try {	
					int idToDelete = scanner.nextInt();
					expenseService.deleteExpense(idToDelete);
				} catch (Exception e) {
					System.out.println(RED + "Invalid input! Please enter a valid expense ID." + RESET);
					scanner.next(); // clear the input buffer
				}
				break;
			case 5:
				System.err.println(GREEN + "Feature not yet implemented");
				break;
			case 6:
				System.out.println("Exiting the App. Goodbye!");
				scanner.close();
				return; // Exit the loop and program
			default:
				System.out.println("Invalid selection. Please choose a valid menu option.");
			}

			expenseService.displayExpenses();
			

		}
	}
}
