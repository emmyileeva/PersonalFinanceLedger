package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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

        // Format the deposit details
        String transactionLine = String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, depositAmount);

        // Save the deposit details to the transactions.csv file
        String fileName = "transactions.csv";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transactionLine + "\n");
            System.out.println("Deposit added successfully! File updated: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the deposit to: " + fileName);
            e.printStackTrace();
        }
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

        // Format the payment details
        String transactionLine = String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, -paymentAmount);

        // Save the payment details to the transactions.csv file
        String fileName = "transactions.csv";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transactionLine + "\n");
            System.out.println("Payment made successfully! File updated: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the payment to: " + fileName);
            e.printStackTrace();
        }
    }

    // Method to handle viewing the ledger
    public static void handleViewLedger(Scanner scanner) {
        // Display the ledger menu
        System.out.println("------ View Ledger ------");
        System.out.println("A) View All Transactions");
        System.out.println("D) View Deposits Only");
        System.out.println("P) View Payments Only");
        System.out.println("0) Back to Main Menu");
        System.out.println("Choose an option: ");

        // Read the user's choice
        String choice = scanner.nextLine().trim().toUpperCase();

        // Handle the user's choice
        switch (choice) {
            case "A":
                viewAllTransactions();
                break;
            case "D":
                System.out.println("Viewing deposits only...");
                // Call a method to view deposits only
                break;
            case "P":
                System.out.println("Viewing payments only...");
                // Call a method to view payments only
                break;
            case "0":
                System.out.println("Returning to main menu...");
                break;
        }
    }

    // Method to handle viewing all transactions
    public static void viewAllTransactions() {
        // Read the transactions from the transactions.csv file
        String fileName = "transactions.csv";

        // Check if the file exists
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("------ All Transactions ------");
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into fields
                String[] fields = line.split("\\|");
                if (fields.length == 5) {
                    // Print the transaction details
                    System.out.printf("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f%n",
                            fields[0], fields[1], fields[2], fields[3], Double.parseDouble(fields[4]));
                    System.out.println("-----------------------------------");
                } else {
                    System.out.println("Invalid transaction format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the transactions from: " + fileName);
            e.printStackTrace();
        }
    }

    // Method to handle viewing deposits only
    public static void viewDepositsOnly() {
    }

    // Method to handle viewing payments only
    public static void viewPaymentsOnly() {
    }


}

