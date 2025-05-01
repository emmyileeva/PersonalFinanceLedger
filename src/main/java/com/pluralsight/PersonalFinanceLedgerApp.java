package com.pluralsight;

import java.util.Scanner;

import static com.pluralsight.LedgerServiceHelper.*;

// This class is the starting point of the application.

public class PersonalFinanceLedgerApp {
    public static void main(String[] args) {

        // Initialize a Scanner object for user input
        try (Scanner scanner = new Scanner(System.in)) {

            // Initialize a boolean variable to control the main loop
            boolean running = true;
            while (running) {

                // Display the main menu
                System.out.println("===========================================");
                System.out.println("🌟 Personal Financial Ledger Home Menu 🌟");
                System.out.println("===========================================");
                System.out.println("|   D) Add Deposit                        |");
                System.out.println("|   P) Make Payment (Debit)               |");
                System.out.println("|   L) View Ledger                        |");
                System.out.println("|   X) Exit                               |");
                System.out.println("===========================================");
                System.out.print("👉 Choose an option: ");

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
                        System.out.println("Exiting, goodbye! 👋");
                        running = false; // Exit the loop
                        break;
                    default:
                        System.out.println("❌ Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}