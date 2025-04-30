package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LedgerFileService {

    // File name for storing transactions
    private static final String FILE_NAME = "transactions.csv";

    // Method to add a deposit transaction
    public static void addDeposit(LedgerTransaction transaction) {
        saveTransaction(transaction);
    }

    // Method to add a payment transaction
    public static void addPayment(LedgerTransaction transaction) {
        saveTransaction(transaction);
    }

    // Method to save all transactions (deposit or payment)
    private static void saveTransaction(LedgerTransaction transaction) {
        // Check if the transaction is valid
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            // Append the transaction to the file
            String transactionLine = String.format("%s|%s|%s|%s|%.2f",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
            // Write the transaction line to the file
            writer.write(transactionLine + "\n");
            System.out.println("Transaction saved successfully to: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the transaction.");
            e.printStackTrace();
        }
    }

    // Method to read all transactions (both deposits and payments)
    public static List<LedgerTransaction> readAllTransactions() {
        // List to store all transactions
        List<LedgerTransaction> transactions = new ArrayList<>();

        // Read transactions from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // This skips empty lines
                }
                String[] fields = line.split("\\|");
                if (fields.length == 5) {
                    LedgerTransaction transaction = new LedgerTransaction(
                            fields[0], // date
                            fields[1], // time
                            fields[2], // description
                            fields[3], // vendor
                            Double.parseDouble(fields[4]) // amount
                    );
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading transactions from: " + FILE_NAME);
            e.printStackTrace();
        }

        return transactions;
    }

    // Method to read deposits only
    public static List<LedgerTransaction> readDepositsOnly() {
        // List to store deposit transactions
        List<LedgerTransaction> deposits = new ArrayList<>();
        // Read all transactions from the file
        List<LedgerTransaction> allTransactions = readAllTransactions();

        // Loop through all transactions and filter deposits
        for (LedgerTransaction transaction : allTransactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }

        return deposits;
    }

    // Method to read payments only
    public static List<LedgerTransaction> readPaymentsOnly() {
        // List to store payment transactions
        List<LedgerTransaction> payments = new ArrayList<>();
        // Read all transactions from the file
        List<LedgerTransaction> allTransactions = readAllTransactions();

        // Loop through all transactions and filter payments
        for (LedgerTransaction transaction : allTransactions) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction);
            }
        }

        return payments;
    }
}
