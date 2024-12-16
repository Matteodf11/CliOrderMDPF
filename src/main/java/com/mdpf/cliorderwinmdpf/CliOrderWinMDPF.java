package com.mdpf.cliorderwinmdpf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Matteo Di Paolo Formiconi
 * @version 1.0
 * 
 */

public class CliOrderWinMDPF {

    private static final List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        final String RESET = "\033[0m";
        final String BLACK_BOLD = "\033[1;30m";
        final String ANSI_CYAN = "\033[0;36m";
        Scanner scanner = new Scanner(System.in);

        clearScreen();
        displayStartupInfo();

        boolean running = true;
        while (running) {
            clearScreen();
            System.out.println(ANSI_CYAN + "Welcome to CliOrderWinMDPF - Version 1.0" + RESET);
            System.out.println("1. Take full order");
            System.out.println("2. Get full order");
            System.out.println("3. Set any field");
            System.out.println("4. About (Press 'A' or 'a')");
            System.out.println("5. Exit (Press 'X' or 'x')");

            char choice = scanner.next().charAt(0);
            scanner.nextLine();

            switch (choice) {
                case '1':
                    takeFullOrder(scanner);
                    break;
                case '2':
                    getFullOrder(scanner);
                    break;
                case '3':
                    setField(scanner);
                    break;
                case 'A':
                case 'a':
                    displayAboutInfo();
                    break;
                case 'X':
                case 'x':
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        clearScreen();
        System.out.println("Thank you for using CliOrderWinMDPF! Goodbye!");
    }

    private static void displayStartupInfo() {
        System.out.println("Welcome to the Digital Service!");
        System.out.println("Current OS: " + System.getProperty("os.name"));
        System.out.println("Current Date and Time: " + new Date());
        System.out.println("Computer Name: " + System.getenv("COMPUTERNAME"));
        System.out.println("Username: " + System.getProperty("user.name"));
        pause();
    }

    private static void takeFullOrder(Scanner scanner) {
        Date currentDate = new Date();

        System.out.println("Take in restaurant or takeaway:");
        System.out.println("1. Takeaway");
        System.out.println("2. Restaurant");
        boolean takeaway = scanner.nextInt() == 1;

        int tableNumber = 0;
        if (!takeaway) {
            System.out.print("Table number: ");
            tableNumber = scanner.nextInt();
        }

        scanner.nextLine(); // Consume newline
        System.out.print("Full Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Customer Comments (leave empty if none): ");
        String customerComments = scanner.nextLine();

        System.out.print("Grand Total: ");
        double grandTotal = scanner.nextDouble();

        System.out.println("Payment Method:");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        String paymentMethod = (scanner.nextInt() == 1) ? "Cash" : "Credit Card";

        System.out.print("Tip (0 for none, or 5, 10, 15, 20): ");
        int tipping = scanner.nextInt();

        System.out.print("Request Invoice? (yes/no): ");
        boolean requestInvoice = scanner.next().equalsIgnoreCase("yes");

        Order order = new Order(currentDate, takeaway, tableNumber, customerName,
                customerEmail, customerComments, grandTotal,
                paymentMethod, tipping, requestInvoice);

        orders.add(order);

        System.out.println("\nOrder details:");
        order.OrderDetails();
        pause();
    }

    private static void getFullOrder(Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                System.out.println("Order " + (i + 1) + ":");
                orders.get(i).OrderDetails();
                System.out.println();
            }
        }
        pause();
    }

    private static void setField(Scanner scanner) {
        System.out.println("Enter the order number you want to update (1-" + orders.size() + "):");
        int orderIndex = scanner.nextInt() - 1;

        if (orderIndex < 0 || orderIndex >= orders.size()) {
            System.out.println("Invalid order number.");
            pause();
            return;
        }

        Order order = orders.get(orderIndex);
        scanner.nextLine();
        System.out.println("Current details: " + order);

        System.out.println("Which field would you like to update?");
        System.out.println("1. Full Customer Name");
        System.out.println("2. Email");
        System.out.println("3. Customer Comments");
        System.out.println("4. Grand Total");
        System.out.println("5. Payment Method");
        System.out.println("6. Tipping");
        System.out.println("7. Request Invoice");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine();

        switch (fieldChoice) {

            case 1:
                clearScreen();
                System.out.print("New Full Customer Name: ");
                String newName = scanner.nextLine();
                order = new Order(order.getDateTime(), order.isTakeaway(), order.getTableNumber(),
                        newName, order.getEmail(), order.getCustomerComments(),
                        order.getGrandTotal(), order.getPaymentMethod(),
                        order.getTipping(), order.isRequestInvoice());
                orders.set(orderIndex, order);

                break;
            case 2:
                System.out.print("New Email: ");
                String newEmail = scanner.nextLine();
                order.setEmail(newEmail);
                break;
            case 3:
                System.out.print("New Customer Comments: ");
                String newComments = scanner.nextLine();
                order = new Order(order.getDateTime(), order.isTakeaway(), order.getTableNumber(),
                        order.getFullCustomerName(), order.getEmail(),
                        newComments, order.getGrandTotal(), order.getPaymentMethod(),
                        order.getTipping(), order.isRequestInvoice());
                orders.set(orderIndex, order);
                break;
            case 4:
                System.out.print("New Grand Total: ");
                double newTotal = scanner.nextDouble();
                order = new Order(order.getDateTime(), order.isTakeaway(), order.getTableNumber(),
                        order.getFullCustomerName(), order.getEmail(),
                        order.getCustomerComments(), newTotal,
                        order.getPaymentMethod(), order.getTipping(),
                        order.isRequestInvoice());
                orders.set(orderIndex, order);
                break;
            case 5:
                System.out.println("New Payment Method:");
                System.out.println("1. Cash");
                System.out.println("2. Credit Card");
                String newPaymentMethod = (scanner.nextInt() == 1) ? "Cash" : "Credit Card";
                order = new Order(order.getDateTime(), order.isTakeaway(), order.getTableNumber(),
                        order.getFullCustomerName(), order.getEmail(),
                        order.getCustomerComments(), order.getGrandTotal(),
                        newPaymentMethod, order.getTipping(), order.isRequestInvoice());
                orders.set(orderIndex, order);
                break;
            case 6:
                System.out.print("New Tip (0 for none, or 5, 10, 15, 20): ");
                int newTip = scanner.nextInt();
                order = new Order(order.getDateTime(), order.isTakeaway(), order.getTableNumber(),
                        order.getFullCustomerName(), order.getEmail(),
                        order.getCustomerComments(), order.getGrandTotal(),
                        order.getPaymentMethod(), newTip, order.isRequestInvoice());
                orders.set(orderIndex, order);
                break;
            case 7:
                System.out.print("Request Invoice? (yes/no): ");
                boolean newRequestInvoice = scanner.next().equalsIgnoreCase("yes");
                order = new Order(order.getDateTime(), order.isTakeaway(), order.getTableNumber(),
                        order.getFullCustomerName(), order.getEmail(),
                        order.getCustomerComments(), order.getGrandTotal(),
                        order.getPaymentMethod(), order.getTipping(),
                        newRequestInvoice);
                orders.set(orderIndex, order);
                break;
            default:
                System.out.println("Invalid option.");
        }

        System.out.println("Order updated successfully.");
        pause();
    }

    private static void displayAboutInfo() {
        System.out.println("Developer: Matteo Di Paolo Formiconi");
        System.out.println("Application: CliOrderWinMDPF");
        System.out.println("Version: 1.0");
        pause();
    }

    private static void clearScreen() {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
// For Windows, you can clear the screen using "cls"
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
// For Unix/Linux and macOS, use ANSI escape codes
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } else {
            System.out.println("Screen clearing is not supported on this system.");
        }

    }

    private static void pause() {
        System.out.println("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
