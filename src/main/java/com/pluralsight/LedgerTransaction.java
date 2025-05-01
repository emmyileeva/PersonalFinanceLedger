package com.pluralsight;

// This class represents one transaction in the ledger (e.g., a deposit or payment).

public class LedgerTransaction {
    // Instance variables to store transaction details
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // Constructor to initialize a LedgerTransaction object
    public LedgerTransaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getters and setters for each instance variable that allow other parts of the program to get or update each value
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
