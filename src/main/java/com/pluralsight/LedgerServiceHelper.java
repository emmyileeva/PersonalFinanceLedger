package com.pluralsight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class LedgerServiceHelper {

    // Method to handle adding a deposit
    public static void handleAddDeposit(Scanner scanner) {

        // Prompt the user for deposit details
        System.out.print("Enter deposit description: ");
        String description = scanner.nextLine();

        // Prompt the user for vendor details
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();

        // Prompt the user for deposit amount
        System.out.print("Enter deposit amount: ");
        double depositAmount = Double.parseDouble(scanner.nextLine());

        // Get the current date and time
        LocalDateTime depositDateTime = LocalDateTime.now();
        String date = depositDateTime.toLocalDate().toString();
        String time = depositDateTime.toLocalTime().withNano(0).toString();

        // Create a ledger transaction object
        LedgerTransaction transaction = new LedgerTransaction(date, time, description, vendor, depositAmount);

        // Save the deposit using ledgerFileService
        LedgerFileService.addDeposit(transaction);
    }

    // Method to handle making a payment
    public static void handleMakePayment(Scanner scanner) {

        // Prompt the user for payment details
        System.out.print("Enter payment description: ");
        String description = scanner.nextLine();

        // Prompt the user for vendor details
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();

        // Prompt the user for payment amount
        System.out.print("Enter payment amount: ");
        double paymentAmount = Double.parseDouble(scanner.nextLine());

        // Get the current date and time
        LocalDateTime paymentDateTime = LocalDateTime.now();
        String date = paymentDateTime.toLocalDate().toString();
        String time = paymentDateTime.toLocalTime().withNano(0).toString();

        // Create a ledger transaction object with a negative amount for payment
        LedgerTransaction transaction = new LedgerTransaction(date, time, description, vendor, -paymentAmount);

        // Save the transaction using LedgerFileService
        LedgerFileService.addPayment(transaction);
    }

    // Method to handle viewing the ledger
    public static void handleViewLedger(Scanner scanner) {
        // Initialize a boolean variable to control the ledger loop
        boolean ledger = true;
        while (ledger) {
            // Display the ledger menu
            System.out.println("------ View Ledger ------");
            System.out.println("A) View All Transactions");
            System.out.println("D) View Deposits Only");
            System.out.println("P) View Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Back to Home Screen");
            System.out.println("Choose an option: ");

            // Read the user's choice
            String choice = scanner.nextLine().trim().toUpperCase();

            // Handle the user's choice
            switch (choice) {
                case "A":
                    viewAllTransactions();
                    break;
                case "D":
                    viewDepositsOnly();
                    break;
                case "P":
                    viewPaymentsOnly();
                    break;
                case "R":
                    boolean goToHome = handleReports(scanner);
                    if (goToHome) {
                        ledger = false; // Go back to the home screen
                    }
                    break;
                case "H":
                    ledger = false; // Exit the ledger loop
                    break;
            }
        }
    }

    // Method to handle viewing all transactions
    public static void viewAllTransactions() {
        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();

        System.out.println("------ All Transactions ------");

        // Loop from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);

            // Print transaction details
            System.out.printf("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f%n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            System.out.println("-----------------------------------");
        }
    }

    // Method to handle viewing deposits only
    public static void viewDepositsOnly() {
        // Read deposits only from the ledger file
        List<LedgerTransaction> deposits = LedgerFileService.readDepositsOnly();

        System.out.println("------ Deposits Only ------");

        // Loop from newest to oldest
        for (int i = deposits.size() - 1; i >= 0; i--) {
            LedgerTransaction t = deposits.get(i);

            // Print deposit details
            System.out.printf("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f%n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            System.out.println("-----------------------------------");
        }
    }

    // Method to handle viewing payments only
    public static void viewPaymentsOnly() {
        // Read payments only from the ledger file
        List<LedgerTransaction> payments = LedgerFileService.readPaymentsOnly();

        System.out.println("------ Payments Only ------");

        // Loop from newest to oldest
        for (int i = payments.size() - 1; i >= 0; i--) {
            LedgerTransaction t = payments.get(i);

            // Print payment details
            System.out.printf("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f%n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            System.out.println("-----------------------------------");
        }
    }

    // Method to handle reports
    public static boolean handleReports(Scanner scanner) {
        // Initialize a boolean variable to control the reports loop
        boolean reports = true;
        while (reports) {
            // Display the reports menu
            System.out.println("------ Reports ------");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Ledger Menu");
            System.out.println("H) Back to Home Screen");
            System.out.print("Choose an option: ");

            // Read the user's choice
            String choice = scanner.nextLine().trim().toUpperCase();

            // Handle the user's choice
            switch (choice) {
                case "1":
                    // Month to Date logic
                    break;
                case "2":
                    // Previous Month logic
                    break;
                case "3":
                    // Year to Date logic
                    break;
                case "4":
                    // Previous Year logic
                    break;
                case "5":
                    // Search by Vendor logic
                    break;
                case "0":
                    reports = false; // Go back to the ledger menu
                    break;
                case "H":
                    return true; // Go back to the home screen
            }
        }
        return false; // Return to the previous menu
    }
}

