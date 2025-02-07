package com.bptn.project.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.bptn.project.models.Expense;

public class FileStorage {

	private static final String FILE_NAME = "expenses.csv";
	private static final String CSV_HEADER = "ID,Category,Amount,Description,Date\n";
	
	//Method to save expense to file
	public static void saveExpenses(List<Expense> expenses) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
			writer.print(CSV_HEADER);

			for (Expense expense : expenses) {
				writer.println(expense.getId() + "," + expense.getCategory() + "," + expense.getAmount() + ","
						+ expense.getDescription().replaceAll(",", " ") + "," + expense.getDate());
			}
			
			System.out.println("Expense saved successfully to " + FILE_NAME);
		} catch (IOException e) {
			System.out.println("Error saving expenses: " + e.getMessage());
		}

	}
	
	
	// Method to load expenses from csv file
	public static List<Expense> loadExpenses(){
		
		List<Expense> expenses = new ArrayList<>();
		File file = new File(FILE_NAME);
		
		if (!file.exists()) {
			return expenses; //Return empty list
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
			String line;
			reader.readLine(); // Skip the header row
			
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				
				if (data.length == 5) {
					int id = Integer.parseInt(data[0]);
					String category = data[1];
					double amount = Double.parseDouble(data[2]);
					String description = data[3];
					String date = data[4];
					
					expenses.add(new Expense(id, category, amount, description, java.time.LocalDate.parse(date)));
				}
			}
		} catch ( Exception e) {
			System.out.println("Error loading expenses: " + e.getMessage());
		}
		
		return expenses;
		
	}

}
