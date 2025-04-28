package com.pluralsight;

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
        // Add logic
    }

    // Method to handle viewing the ledger
    public static void handleViewLedger(Scanner scanner) {
        // Add logic
    }
}

