package com.pluralsight.ui;

import com.pluralsight.utillities.*;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner read = new Scanner(System.in);
        boolean running = true;

        LogoDisplay.showBanner();


        while (running) {
            // Main menu display1

            LogoDisplay.showMascot();
            System.out.println(Colors.KAPPA_GREEN + "===== Welcome to Café Kappa =====" + Colors.RESET);
            System.out.println("1) Start New Order");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");

            // input validator
            int choice = getValidInt(read);
            Loading.kappaLoader();

            switch (choice) {
                case 1 -> OrderScreen.newOrder(read);
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                    LogoDisplay.showMascot();
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        read.close();
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
