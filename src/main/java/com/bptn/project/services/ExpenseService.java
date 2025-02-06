package com.bptn.project.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bptn.project.models.Expense;

public class ExpenseService {

	// Create an arrayList for the expenses
	List<Expense> expenses = new ArrayList<>();

	private int nextId = 1;

	// Convert categoryList to an arrayList
	String[] categoryList = { "Food & Dining", "Transport", "Shopping", "Utilities", "Healthcare", "Entertainment",
			"Education", "Savings", "Investment", "Miscellaneous" };
	List<String> categoryArrayList = new ArrayList<>(Arrays.asList(categoryList));

	// Import the scanner method
	static Scanner scanner = new Scanner(System.in);

	// Method to select category
	private String selectCategory() {
		while (true) {
			System.out.println("Please select a category for your expense: ");
			for (int i = 0; i < categoryArrayList.size(); i++) {
				System.out.println((i + 1) + ". " + categoryArrayList.get(i));
			}
			try {
				int categorySelection = scanner.nextInt();
				if (categorySelection <= categoryArrayList.size() && categorySelection > 0) {
					return categoryArrayList.get(categorySelection - 1);
				} else {
					System.out.println("Invalid selection. Please try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Invalid input. Please enter a number between 1 and 10");
				scanner.next(); // clear the input buffer
			}
		}
	}

	// Method to input amount
	public double inputAmount() {
		// Amount input
		while (true) {
			System.out.println("Enter an amount: ");
			try {
				double amount = scanner.nextDouble();
				if (amount > 0.0) {
					return amount;
				} else {
					System.out.println("Amount must be greater than zero.");
				}

			} catch (InputMismatchException e) {
				System.out.println("Error: Please enter a validnumeric value.");
				scanner.next(); // clear the input buffer
			}
		}
	}

	// Method to input description
	public String inputDescription() {
		scanner.nextLine(); // Clear scanner buffer before taking the next input
		System.out.println("Please enter a description for the expense: ");
		return scanner.nextLine();
	}

	// Method to add a new expense
	public void addExpense() {
		String category = selectCategory();
		double amount = inputAmount();
		String description = inputDescription();
		LocalDate date = LocalDate.now();
		// Create expense object
		Expense expense = new Expense(nextId++, category, amount, description, date);
		expenses.add(expense); // add to the ArrayList
		System.out.println("Expense added successfully");
	}

	// Method to update an expense
	public void editExpense() {
		if (expenses.isEmpty()) {
			System.out.println("There are no expenses to edit.");
			return;
		}

		System.out.print("Enter the ID of the expense you want to edit:");
		int idInput = scanner.nextInt();

		Expense expenseToEdit = null; // set the expense to edit to null

		// Find the expense
		for (Expense expense : expenses) {
			if (expense.getId() == idInput) {
				expenseToEdit = expense;
				break;
			}
		}

		// Handle if expense not found
		if (expenseToEdit == null) {
			System.out.println("Expense with ID " + idInput + " not found");
			return;
		}

		while (true) {
			System.out.println("Select the field you want to edit: ");
			System.out.println("1. Category");
			System.out.println("2. Amount");
			System.out.println("3. Description");
			System.out.println("4. Exit Edit Menu");

			int choice = scanner.nextInt();
			scanner.nextLine(); // clear buffer

			switch (choice) {
			case 1:
				expenseToEdit.setCategory(selectCategory());
				System.out.println("Category updated successfully");
				break;
			case 2:
				expenseToEdit.setAmount(inputAmount());
				System.out.println("Amount updated successfully");
				break;
			case 3:
				expenseToEdit.setDescription(inputDescription());
				System.out.println("Description updated successfully");
				break;
			case 4:
				System.out.println("Exiting edit menu.");
				return;
			default: 
				System.out.println("Invalid choice. Please enter a umber between 1 and 4.");
			}
		}

	}

	// Method to delete expense
	public void deleteExpense(int id) {

	}

	// Method to retrieve all expenses
	public List<Expense> getAllExpenses() {
		List<Expense> currentExpenses = new ArrayList<>();
		return currentExpenses;
	}

}
