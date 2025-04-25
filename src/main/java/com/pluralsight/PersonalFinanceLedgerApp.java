package com.pluralsight;
import java.util.Scanner;
import java.time.LocalDateTime;

public class PersonalFinanceLedgerApp {
    public static void main(String[] args) {

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the main menu
            System.out.println("------ Personal Finance Ledger ------");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) View Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            // Read the user's choice
            String choice = scanner.nextLine().trim().toUpperCase();

            // Handle the user's choice
            switch (choice) {
                case "D":
                    handleAddDeposit(scanner);
                    break;
                case "P":
                    handleMakePayment(scanner);
                    break;
                case "L":
                    handleViewLedger(scanner);
                    break;
                case "X":
                    System.out.println("Exiting, goodbye!");
                    System.exit(0);
                    break;
            }
        }
    }

    // Method to handle adding a deposit
    private static void handleAddDeposit(Scanner scanner) {
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

        // Print transactionLine to test the output
        System.out.println("Transaction Line: " + transactionLine);
    }

    // Method to handle making a payment
    private static void handleMakePayment(Scanner scanner) {
        // Add logic
    }

    // Method to handle viewing the ledger
    private static void handleViewLedger(Scanner scanner) {
        // Add logic
    }
}
