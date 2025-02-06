package com.bptn.project;

import java.util.Scanner;

import com.bptn.project.services.ExpenseService;

public class App {
	public static void main(String[] args) {
		// Create an instance of the Expense service
		ExpenseService expenseService = new ExpenseService();
		
		// Import the scanner method
		Scanner scanner = new Scanner(System.in);
		
	
			System.out.println("Welcome to the expense tracker program.");
			System.out.println("Main Menu. Please choose among the following options: ");
			System.out.println("1: Add a new expense");
			System.out.println("2: View your expenses");
			System.out.println("3: Edit an expense");
			System.out.println("3: Delete new expense");
			System.out.println("5: Exit Main Menu");
			
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
