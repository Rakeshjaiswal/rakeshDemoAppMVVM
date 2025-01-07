package com.example.rakeshdemotestretrofit.model;


public class Transaction {
    private final int id;
    private final String date;
    private final int amount;
    private final String category;
    private final String description;

    // Constructor
    public Transaction(int id, String date, int amount, String category, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}