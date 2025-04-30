package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class LedgerServiceHelper {

    // Method to handle adding a deposit
    public static void handleAddDeposit(Scanner scanner) {

        System.out.println("===========================================");
        System.out.println("           💰 Add Deposit                 ");
        System.out.println("===========================================");

        // Prompt the user for deposit details
        System.out.print("📝 Enter deposit description: ");
        String description = scanner.nextLine();

        // Prompt the user for vendor details
        System.out.print("🏢 Enter vendor name: ");
        String vendor = scanner.nextLine();

        // Validate the deposit amount
        double depositAmount = 0;
        // Loop until a valid deposit amount is entered
        while (true) {
            // Prompt the user for deposit amount
            System.out.print("💵 Enter deposit amount: ");
            String input = scanner.nextLine();
            try {
                // Parse the input to a double
                depositAmount = Double.parseDouble(input);
                if (depositAmount <= 0) {
                    System.out.println("❌ Deposit amount must be greater than zero. Please try again.");
                } else {
                    break; // Exit the loop if the input is valid
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

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

        System.out.println("===========================================");
        System.out.println("           🛒 Make Payment                ");
        System.out.println("===========================================");

        // Prompt the user for payment details
        System.out.print("📝 Enter payment description: ");
        String description = scanner.nextLine();

        // Prompt the user for vendor details
        System.out.print("🏢 Enter vendor name: ");
        String vendor = scanner.nextLine();

        // Validate the payment amount
        double paymentAmount = 0;
        // Loop until a valid payment amount is entered
        while (true) {
            // Prompt the user for payment amount
            System.out.print("💵 Enter payment amount: ");
            String input = scanner.nextLine();
            try {
                // Parse the input to a double
                paymentAmount = Double.parseDouble(input);
                if (paymentAmount <= 0) {
                    System.out.println("❌  Payment amount must be greater than zero. Please try again.");
                } else {
                    break; // Exit the loop if the input is valid
                }
            } catch (NumberFormatException e) {
                System.out.println("❌  Invalid input. Please enter a valid number.");
            }
        }

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
            System.out.println("===========================================");
            System.out.println("              📒 Ledger Menu            ");
            System.out.println("===========================================");
            System.out.println("|   A) View All Transactions              |");
            System.out.println("|   D) View Deposits Only                 |");
            System.out.println("|   P) View Payments Only                 |");
            System.out.println("|   R) Reports                            |");
            System.out.println("|   H) Back to Home Screen                |");
            System.out.println("===========================================");
            System.out.print("👉 Choose an option: ");

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
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Method to handle viewing all transactions
    public static void viewAllTransactions() {
        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();

        // Display the header for all transactions
        System.out.println("===========================================");
        System.out.println("           📒 All Transactions            ");
        System.out.println("===========================================");

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);

            // Print transaction details in a formatted table
            System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }
        System.out.println("===========================================");
    }

    // Method to handle viewing deposits only
    public static void viewDepositsOnly() {
        // Read deposits only from the ledger file
        List<LedgerTransaction> deposits = LedgerFileService.readDepositsOnly();

        // Display the header for deposits only
        System.out.println("===========================================");
        System.out.println("           💰 Deposits Only               ");
        System.out.println("===========================================");

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = deposits.size() - 1; i >= 0; i--) {
            LedgerTransaction t = deposits.get(i);

            // Print deposit details in a formatted table
            System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }
        System.out.println("===========================================");
    }

    // Method to handle viewing payments only
    public static void viewPaymentsOnly() {
        // Read payments only from the ledger file
        List<LedgerTransaction> payments = LedgerFileService.readPaymentsOnly();

        // Display the header for payments only
        System.out.println("===========================================");
        System.out.println("           🛒 Payments Only               ");
        System.out.println("===========================================");

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = payments.size() - 1; i >= 0; i--) {
            LedgerTransaction t = payments.get(i);

            // Print payment details in a formatted table
            System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }
        System.out.println("===========================================");
    }

    // Method to handle reports
    public static boolean handleReports(Scanner scanner) {
        // Initialize a boolean variable to control the reports loop
        boolean reports = true;
        while (reports) {
            // Display the reports menu
            System.out.println("===========================================");
            System.out.println("              📊 Reports Menu           ");
            System.out.println("===========================================");
            System.out.println("|   1) Month to Date                      |");
            System.out.println("|   2) Previous Month                     |");
            System.out.println("|   3) Year to Date                       |");
            System.out.println("|   4) Previous Year                      |");
            System.out.println("|   5) Search by Vendor                   |");
            System.out.println("|   6) Custom Search                      |");
            System.out.println("|   7) Summary Reports                    |");
            System.out.println("|   0) Back to Ledger Menu                |");
            System.out.println("|   H) Back to Home Screen                |");
            System.out.println("===========================================");
            System.out.print("👉 Choose an option: ");

            // Read the user's choice
            String choice = scanner.nextLine().trim().toUpperCase();

            // Handle the user's choice
            switch (choice) {
                case "1":
                    viewMonthToDateTransactions();
                    break;
                case "2":
                    viewPreviousMonthTransactions();
                    break;
                case "3":
                    viewYearToDateTransactions();
                    break;
                case "4":
                    viewPreviousYearTransactions();
                    break;
                case "5":
                    searchByVendor(scanner);
                    break;
                case "6":
                    customSearch(scanner);
                    break;
                case "7":
                    viewSummaryReport();
                    break;
                case "0":
                    reports = false; // Go back to the ledger menu
                    break;
                case "H":
                    return true; // Go back to the home screen
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
                    break;
            }
        }
        return false; // Return to the previous menu
    }

    // Method to view month-to-date transactions
    public static void viewMonthToDateTransactions() {
        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Display the header for month-to-date transactions
        System.out.println("===========================================");
        System.out.println("       📅 Month-to-Date Transactions       ");
        System.out.println("===========================================");

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);
            LocalDate transactionDate = LocalDate.parse(t.getDate());

            // Check if the transaction date is within the current month
            if (transactionDate.getMonth() == now.getMonth() &&
                    transactionDate.getYear() == now.getYear()) {
                // Print transaction details in a formatted table
                System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        System.out.println("===========================================");
    }

    // Method to view previous month transactions
    public static void viewPreviousMonthTransactions() {
        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Calculate the previous month and year using minusMonths
        LocalDate previousMonth = now.minusMonths(1).toLocalDate();
        int previousMonthValue = previousMonth.getMonthValue();
        int previousYearValue = previousMonth.getYear();

        // Display the header for previous month transactions
        System.out.println("===========================================");
        System.out.println("       📅 Previous Month Transactions      ");
        System.out.println("===========================================");

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);
            LocalDate transactionDate = LocalDate.parse(t.getDate());

            // Check if the transaction date is within the previous month
            if (transactionDate.getMonthValue() == previousMonthValue &&
                    transactionDate.getYear() == previousYearValue) {
                // Print transaction details in a formatted table
                System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        System.out.println("===========================================");
    }

    // Method to view year-to-date transactions
    public static void viewYearToDateTransactions() {
        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();
        // Get the current year
        int currentYear = LocalDate.now().getYear();

        // Display the header for year-to-date transactions
        System.out.println("===========================================");
        System.out.println("        📅 Year-to-Date Transactions       ");
        System.out.println("===========================================");

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);
            LocalDate transactionDate = LocalDate.parse(t.getDate());

            // Check if the transaction date is within the current year
            if (transactionDate.getYear() == currentYear) {
                // Print transaction details in a formatted table
                System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        System.out.println("===========================================");
    }

    // Method to view previous year transactions
    public static void viewPreviousYearTransactions() {
        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();
        // Get the previous year
        int previousYear = LocalDate.now().getYear() - 1;

        // Display the header for previous year transactions
        System.out.println("===========================================");
        System.out.println("       📅 Previous Year Transactions       ");
        System.out.println("===========================================");

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);
            LocalDate transactionDate = LocalDate.parse(t.getDate());

            // Check if the transaction date is within the previous year
            if (transactionDate.getYear() == previousYear) {
                // Print transaction details in a formatted table
                System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        System.out.println("===========================================");
    }

    // Method to search by vendor
    public static void searchByVendor(Scanner scanner) {
        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();

        // Display the header for vendor search
        System.out.println("===========================================");
        System.out.println("           🔍 Search by Vendor             ");
        System.out.println("===========================================");

        // Prompt the user for vendor name
        System.out.print("👉 Enter vendor name to search: ");
        String vendorName = scanner.nextLine().trim().toLowerCase();

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);
            // Check if the transaction vendor matches the search term
            if (t.getVendor().toLowerCase().contains(vendorName)) {
                // Print transaction details in a formatted table
                System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
        // If no transactions found for the vendor
        if (transactions.isEmpty()) {
            System.out.println("❌ No transactions found for the specified vendor.");
        }
    }

    // Method for a user to custom search transactions
    public static void customSearch(Scanner scanner) {
        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();

        // Display the header for custom search
        System.out.println("===========================================");
        System.out.println("           🔍 Custom Search                ");
        System.out.println("===========================================");

        // Prompt the user to enter start date
        System.out.print("👉 Enter Start Date (YYYY-MM-DD) or leave blank: ");
        String startDateInput = scanner.nextLine().trim();
        // Initialize start date to null
        LocalDate startDate = null;
        // If the user provided a start date, parse it
        if (!startDateInput.isEmpty()) {
            try {
                startDate = LocalDate.parse(startDateInput);
            } catch (Exception e) {
                System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
                return; // Exit the method if the date format is invalid
            }
        }

        // Prompt the user to enter end date
        System.out.print("👉 Enter End Date (YYYY-MM-DD) or leave blank: ");
        String endDateInput = scanner.nextLine().trim();
        // Initialize end date to null
        LocalDate endDate = null;
        // If the user provided an end date, parse it
        if (!endDateInput.isEmpty()) {
            try {
                endDate = LocalDate.parse(endDateInput);
            } catch (Exception e) {
                System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
                return; // Exit the method if the date format is invalid
            }
        }

        // Prompt the user to enter description
        System.out.print("👉 Enter Description or leave blank: ");
        String description = scanner.nextLine().trim().toLowerCase();

        // Prompt the user to enter vendor name
        System.out.print("👉 Enter Vendor Name or leave blank: ");
        String vendorName = scanner.nextLine().trim().toLowerCase();

        // Prompt the user to enter amount
        System.out.print("👉 Enter Amount (exact) or leave blank: ");
        String amountInput = scanner.nextLine().trim();
        // Initialize amount to null
        Double amount = null;
        // If the user provided an amount, parse it
        if (!amountInput.isEmpty()) {
            try {
                amount = Double.parseDouble(amountInput);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount format. Please enter a valid number.");
                return; // Exit the method if the amount format is invalid
            }
        }

        // Print the table header
        System.out.println("===========================================");
        System.out.println("           📋 Search Results               ");
        System.out.println("===========================================");

        // Print the table header
        System.out.printf("| %-12s | %-8s | %-20s | %-15s | %-10s |\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        // Loop from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);
            LocalDate transactionDate = LocalDate.parse(t.getDate());

            // Initialize a boolean variable to track if the transaction matches the filters
            boolean matches = true;

            // Check if start date is provided and if the transaction date is before it
            if (startDate != null && transactionDate.isBefore(startDate)) {
                matches = false;
            }
            // Check if end date is provided and if the transaction date is after it
            if (endDate != null && transactionDate.isAfter(endDate)) {
                matches = false;
            }
            // Check if description is provided and if it matches
            if (!description.isEmpty() && !t.getDescription().toLowerCase().contains(description)) {
                matches = false;
            }
            // Check if vendor name is provided and if it matches
            if (!vendorName.isEmpty() && !t.getVendor().toLowerCase().contains(vendorName)) {
                matches = false;
            }
            // Check if amount is provided and if it matches
            if (amount != null && t.getAmount() != amount) {
                matches = false;
            }
            // If all filters match, print the transaction details
            if (matches) {
                System.out.printf("| %-12s | %-8s | %-20s | %-15s | %10.2f |\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                System.out.println("-----------------------------------");
            }
        }
    }
    // Method to create summary reports
    public static void viewSummaryReport() {

        // Read all transactions from the ledger file
        List<LedgerTransaction> transactions = LedgerFileService.readAllTransactions();

        // Initialize variables for total deposits and total expenses
        double totalDeposits = 0.0; // holds all positive transactions
        double totalPayments = 0.0; // holds all negative transactions

        // Loop through all transactions from newest to oldest
        for (int i = transactions.size() - 1; i >= 0; i--) {
            LedgerTransaction t = transactions.get(i);
            // Check if the transaction amount is positive (deposit)
            if (t.getAmount() > 0) {
                totalDeposits += t.getAmount(); // Add to total deposits
            } else {
                totalPayments += t.getAmount(); // Add to total expenses
            }
        }

        // Calculate total balance
        double totalBalance = totalDeposits + totalPayments;

        // Display the summary report header
        System.out.println("===========================================");
        System.out.println("           📊 Summary Report              ");
        System.out.println("===========================================");

        // Print the summary report in a formatted table
        System.out.printf("| %-25s | %-15s |\n", "Category", "Amount (USD)");
        System.out.println("-------------------------------------------");
        System.out.printf("| %-25s | %15.2f |\n", "💰 Total Deposits", totalDeposits);
        System.out.printf("| %-25s | %15.2f |\n", "🛒 Total Payments", totalPayments);
        System.out.println("-------------------------------------------");
        System.out.printf("| %-25s | %15.2f |\n", "📋 Total Balance", totalBalance);
        System.out.println("===========================================");
    }
}