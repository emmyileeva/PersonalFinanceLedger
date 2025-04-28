package com.pluralsight;

import java.util.Scanner;

import static com.pluralsight.LedgerServiceHelper.*;

public class PersonalFinanceLedgerApp {
    public static void main(String[] args) {

        // Initialize a Scanner object for user input
        try (Scanner scanner = new Scanner(System.in)) {

            // Initialize a boolean variable to control the main loop
            boolean running = true;
            while (running) {

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
                        running = false; // Exit the loop
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}