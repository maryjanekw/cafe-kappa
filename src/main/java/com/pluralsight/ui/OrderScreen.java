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

    public static void newOrder(Scanner read) throws IOException {
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
                case 2 -> MenuDisplay.showCategoryMenu(read, menuMap);
                case 3 -> {
                    System.out.println("\nStarting a new order...\n");
                    handleAddItem(read, menuMap, addOns, order);
                }
                case 4 -> System.out.println("\nCurrent order:\n " + order.buildReceiptText());
                case 5 -> checkout(read, order);
                case 0 -> {
                    System.out.println("Order cancelled.");
                    ordering = false;
                }
                default -> System.out.println("Invalid choice. Try again");
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
            System.out.printf("%d) %-35s Small:$%.2f  Medium:$%.2f  Large:$%.2f%n",
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

        System.out.printf("#%d: %s%n", selected.getItemNumber(), selected.getName());

        // Default size
        String size = "small";

        // Drink Size
        if (selected instanceof Drink) {
            Drink drink = (Drink) selected;

            while (true) {
                System.out.print("Enter size (small,medium,large): ");
                size = read.nextLine().trim().toLowerCase();

                if (size.isEmpty()) size = "small"; // Default size
                if (List.of("small", "medium", "large").contains(size)) break;

                System.out.println("Invalid size");
            }

            CustomDrink customDrink = new CustomDrink(drink);
            double price = customDrink.calculatedPrice();
            System.out.printf("Added %s (%s) - $%.2f%n", drink.getName(), size, price);
        } else { // default
            double price = selected.getPrice("small");
            System.out.printf("Added %s - $%.2f%n", selected.getName(), price);
        }

        // Item Quantity
        System.out.print("Quantity: ");
        int qty = Math.max(1, getValidInt(read));
        System.out.println("Quantity selected: " + qty);


        // Add Add-ons
        List<AddOn> chosenAddOns = new ArrayList<>();
        if (selected instanceof Drink) {
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

        // Display item confirmation
        System.out.println("\n--- Order Confirmation ---");
        System.out.println("Item #: " + selected.getItemNumber());
        System.out.println("Name: " + selected.getName());
        System.out.println("Size: " + size);
        System.out.println("Quantity: " + qty);

        if (chosenAddOns.isEmpty()) {
            System.out.println("Add-ons: none");
        } else {
            System.out.print("Add-ons: ");
            for (int i = 0; i < chosenAddOns.size(); i++) {
                System.out.print(chosenAddOns.get(i).getName());
                if (i < chosenAddOns.size() - 1) System.out.print(", ");
            }
            System.out.println();
        }

        System.out.println("---------------------------\n");

    }


    // Checkout method
    private static void checkout(Scanner read, Order order) throws IOException {
        if (order == null || order.isEmpty()){
            System.out.println("Checkout is empty!");
            return;
        }

        // Print cart summary
        order.printSummary();

        // Confirms purchase
        System.out.println("\nWould you like to complete this order? (yes/no): ");
        String confirm = read.nextLine().trim().toLowerCase();
        if (!confirm.equals("yes")){
            System.out.println("Canceling order... \nReturn to menu...");
            return;
        }
        ReceiptWriter.writeReceipt(order);
        order.buildReceiptText();
        System.out.println("Order complete!");
        order.clear();
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

