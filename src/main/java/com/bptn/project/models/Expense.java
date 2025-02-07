package com.bptn.project.models;

import java.time.LocalDate;

import javax.lang.model.element.NestingKind;

public class Expense {

	// Instance methods
	private int id;
	private LocalDate date;
	private String category;
	private double amount;
	private String description;

	// Generate getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Constructors
	public Expense() {
	}

	public Expense(int id,  String category, double amount, String description, LocalDate date) {
		super();
		this.id = id;
		this.date = date;
		this.category = category;
		this.amount = amount;
		this.description = description;
	}
	

	
	@Override
	public String toString() {
		return id + " | " + category + " | " + amount + " | " + description + " | " + date;
	}

}
