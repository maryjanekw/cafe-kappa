package com.pluralsight.ui;

import com.pluralsight.data.*;
import com.pluralsight.orders.*;
import com.pluralsight.drinks.*;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class OrderScreen {

    // Display csv files
    private static final String COFFEE_CSV = "coffee.csv";
    private static final String TEA_CSV = "tea.csv";
    private static final String SIGNATURE_CSV = "signaturedrinks.csv";
    private static final String FOOD_CSV = "food.csv";
    private static final String ADDONS_CSV = "addons.csv";

    public static void newOrder(Scanner read) {
        // Menus and add=ons containers
        Map<Integer, MenuItem> menuMap = new TreeMap<>();
        List<AddOn> addOns = new ArrayList<>();

        // To load menus and add-ons
        loadMenusIntoMap(menuMap);
        addOns.addAll(AddOnLoader.loadAddOn(ADDONS_CSV));

        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            // Order Menu
            System.out.println("\n====== Order Menu ======");
            System.out.println("1) Show full menu");
            System.out.println("2) Show menu by category");
            System.out.println("3) Add item");
            System.out.println("4) Review order");
            System.out.println("5) Checkout");
            System.out.println("0) Cancel order and return to main menu");
            System.out.println("Choose: ");

            int choice = getValidInt(read);

            switch (choice) {
                case 1 -> displayFullMenu(menuMap);
            //    case 2 ->
                case 3 -> handleAddItem(read, menuMap, addOns, order);
                case 4 -> System.out.println("\nCurrent order:\n " + order.buildReceiptText());
                case 5 -> {
                    if (order.isEmpty()) {
                        System.out.println("Order is empty; please add at least one item before checkout.");
                    } else {
                        try {
                            Path receipt = ReceiptWriter.writeReceipt(order);
                            System.out.println("Receipt written to: " + receipt.toAbsolutePath());
                        } catch (IOException e) {
                            System.err.println("Failed to write receipt: " + e.getMessage());
                        }
                        ordering = false;
                    }
                }
                case 0 -> {
                    System.out.println("Order cancelled.");
                    ordering = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // --- Helper Methods ---

    // Search by item number
    private static void loadMenusIntoMap (Map < Integer, MenuItem > menuMap) {
        List<MenuItem> coffees = MenuLoader.loadMenu(COFFEE_CSV);
        List<MenuItem> teas = MenuLoader.loadMenu(TEA_CSV);
        List<MenuItem> sig = MenuLoader.loadMenu(SIGNATURE_CSV);
        List<MenuItem> foods = MenuLoader.loadMenu(FOOD_CSV);

        for (MenuItem m : coffees) menuMap.put(m.getItemNumber(), m);
        for (MenuItem m : teas) menuMap.put(m.getItemNumber(), m);
        for (MenuItem m : sig) menuMap.put(m.getItemNumber(), m);
        for (MenuItem m : foods) menuMap.put(m.getItemNumber(), m);
    }

    // Display Full Menu
    public static void displayFullMenu(Map<Integer, MenuItem> menuMap) {
        System.out.println("\n--- Café Kappa Full Menu ---");
        for (MenuItem m : menuMap.values()) {
            System.out.printf("%d) %-30s Small:$%.2f  Medium:$%.2f  Large:$%.2f%n",
                    m.getItemNumber(), m.getName(),
                    m.getPrice("small"), m.getPrice("medium"), m.getPrice("large"));
        }
    }

    // Order Input
    private static void handleAddItem(Scanner read, Map<Integer, MenuItem> menuMap,
                                      List<AddOn> addOns, Order order) {
        System.out.print("Enter item number: ");
        int itemNum = getValidInt(read);
        MenuItem selected = menuMap.get(itemNum);
        if (selected == null) {
            System.out.println("Item number not found.");
            return;
        }

        // Default size
        String size = "small";

        // Drink Size
        if (selected instanceof Drink) {
            Drink drink = (Drink) selected;
            System.out.print("Enter size (small/medium/large): ");
            String input = read.nextLine().trim().toLowerCase();
            if (size.isEmpty()) size = "small";

            CustomDrink customDrink = new CustomDrink(drink);
            double price = customDrink.calculatedPrice();
            System.out.printf("Added %s (%s) - $%.2f%n", drink.getName(), size, price);
        } else {
            double price = selected.getPrice("small");
            System.out.printf("Added %s - $%.2f%n", selected.getName(), price);
        }

        // Item Quantity
        System.out.print("Quantity: ");
        int qty = Math.max(1, getValidInt(read));

        // Add Add-ons
        List<AddOn> chosenAddOns = new ArrayList<>();
        if (!addOns.isEmpty()) {
            System.out.println("\nAvailable add-ons:");
            for (int i = 0; i < addOns.size(); i++) {
                AddOn a = addOns.get(i);
                System.out.printf("%d) %s %s %n", i + 1, a.getName(),
                        a.isFree() ? "(Free)" : String.format("($%.2f)", a.getPrice()));
            }
            System.out.println("Enter add-on numbers separated by commas, or enter to skip:");
            String line = read.nextLine().trim();
            if (!line.isEmpty()) {
                String[] tokens = line.split("\\s*,\\s*");
                for (String t : tokens) {
                    try {
                        int idx = Integer.parseInt(t) - 1;
                        if (idx >= 0 && idx < addOns.size()) {
                            chosenAddOns.add(addOns.get(idx));
                        } else {
                            System.out.println("Ignoring invalid add-on index: " + (idx + 1));
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Ignoring invalid token: " + t);
                    }
                }
            }
        }

        // Display chosen item
        order.addMenuItem(selected, size, chosenAddOns, qty);
        System.out.printf("Added %d x %s (%s) to order.%n", qty, selected.getName(), size);
    }

    // Size prompt
    private static String getSizeFromUser(Scanner scanner, MenuItem selected) {
        System.out.print("Choose size (small/medium/large) [default small]: ");
        String sizeInput = scanner.nextLine().trim().toLowerCase();
        if (sizeInput.isEmpty()) return "small";
        return switch (sizeInput) {
            case "small", "medium", "large" -> sizeInput;
            default -> {
                System.out.println("Invalid size, defaulting to small.");
                yield "small";
            }
        };
    }

    // Input Validator
    private static int getValidInt(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}

