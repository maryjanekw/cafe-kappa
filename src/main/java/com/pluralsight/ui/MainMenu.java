package com.pluralsight.ui;


import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Main menu display
            System.out.println("===== Welcome to Café Kappa =====");
            System.out.println("1) Start New Order");
            System.out.println("2) Display Menu");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");

            // input validator
            int choice = getValidInt(read);

            switch (choice) {
                case 1 -> { // New order
                    System.out.println("\nStarting a new order...\n");
                }
                case 2 ->{ // Menu display

                }
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Input validation Helper
    private static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }
    }
}
