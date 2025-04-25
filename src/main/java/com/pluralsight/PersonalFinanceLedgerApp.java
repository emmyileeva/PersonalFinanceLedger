package com.pluralsight;
import java.util.Scanner;

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
                    handleAddDeposit();
                    break;
                case "P":
                    handleMakePayment();
                    break;
                case "L":
                    handleViewLedger();
                    break;
                case "X":
                    System.out.println("Exiting, goodbye!");
                    System.exit(0);
                    break;
            }
        }
    }

    // Method to handle adding a deposit
    private static void handleAddDeposit() {
        // Add logic
    }

    // Method to handle making a payment
    private static void handleMakePayment() {
        // Add logic
    }

    // Method to handle viewing the ledger
    private static void handleViewLedger() {
        // Add logic
    }
}
