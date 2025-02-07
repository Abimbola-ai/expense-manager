package com.bptn.project.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bptn.project.models.Expense;
import com.bptn.project.storage.FileStorage;

public class ExpenseService {

	// Define color codes
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";

	// Create an arrayList for the expenses
	List<Expense> expenses = FileStorage.loadExpenses();

	// Initialize nextId based on existing expenses
	private int nextId = expenses.isEmpty() ? 1 : expenses.stream().mapToInt(Expense::getId).max().getAsInt() + 1;

	// Convert categoryList to an arrayList
	String[] categoryList = { "Food & Dining", "Transport", "Shopping", "Utilities", "Healthcare", "Entertainment",
			"Education", "Savings", "Investment", "Miscellaneous" };
	List<String> categoryArrayList = new ArrayList<>(Arrays.asList(categoryList));

	// Import the scanner method
	static Scanner scanner = new Scanner(System.in);

	// Method to select category
	private String selectCategory() {
		while (true) {
			System.out.println(GREEN + "Please select a category for your expense: " + RESET);
			for (int i = 0; i < categoryArrayList.size(); i++) {
				System.out.println((i + 1) + ". " + categoryArrayList.get(i));
			}
			try {
				int categorySelection = scanner.nextInt();
				if (categorySelection <= categoryArrayList.size() && categorySelection > 0) {
					return categoryArrayList.get(categorySelection - 1);
				} else {
					System.out.println(RED + "Invalid selection. Please try again." + RESET);
				}
			} catch (InputMismatchException e) {
				System.out.println(RED + "Error: Invalid input. Please enter a number between 1 and 10" + RESET);
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
					System.out.println(RED + "Amount must be greater than zero." + RESET);
				}

			} catch (InputMismatchException e) {
				System.out.println(RED + "Error: Please enter a validnumeric value." + RESET);
				scanner.next(); // clear the input buffer
			}
		}
	}

	// Method to input description
	public String inputDescription() {
//		if (scanner.hasNextLine()) {
//			scanner.nextLine(); // Clear scanner buffer before taking the next input
//		}
		
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
		FileStorage.saveExpenses(expenses); // Save to file
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
			System.out.println(RED + "1." + RESET + GREEN + "Category");
			System.out.println(RED + "2." + RESET + GREEN + "Amount");
			System.out.println(RED + "3." + RESET + GREEN + "Description");
			System.out.println(RED + "4." + RESET + GREEN +"Exit Edit Menu");

			
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
				FileStorage.saveExpenses(expenses);
				return;
			default:
				System.out.println("Invalid choice. Please enter a umber between 1 and 4.");
			}
		}

	}

	// Method to delete an expense by id
	public void deleteExpense(int id) {
		Expense expenseToRemove = null;
		
		for (Expense expense: expenses) {
			if (expense.getId() == id) {
				expenseToRemove = expense;
				break; // Exit the loop after finding the expense
			}
			
		}
		
		if (expenseToRemove != null) {
			expenses.remove(expenseToRemove);
			FileStorage.saveExpenses(expenses); // Save changes
			System.out.println("Expense with ID " + id + " has been removed.");
		} else {
			System.out.println("Expense with ID " + id + "  not found.");
		}

	}
	

	// Method to retrieve all expenses
	public List<Expense> getAllExpenses() {
		return expenses;
	}
	
	// Method to display the expense table.
	public void displayExpenses() {
		if (expenses.isEmpty()) {
			System.out.println(RED + "No expenses found." + RESET);
			return;
		}
		System.out.println("\n" + GREEN + "Your Expenses:" + RESET);
	    System.out.println(RED + "--------------------------------------------------------------------------" + RESET);
	    System.out.printf(RED + "| %-3s | %-15s | %-10s | %-25s | %-10s |\n" + RESET, 
	                      "ID", "Category", "Amount", "Description", "Date");
	    System.out.println(RED + "--------------------------------------------------------------------------" + RESET);
	    
	    for (Expense expense: expenses) {
	    	System.out.printf("| %-3d | %-15s | %-10.2f | %-25s | %-10s |\n",expense.getId(), expense.getCategory(), expense.getAmount(),
                    expense.getDescription().length() > 25 ? expense.getDescription().substring(0, 22) + "...": expense.getDescription(), expense.getDate());
	    }
	    System.out.println(RED + "--------------------------------------------------------------------------" + RESET);
	}
	
	

}
