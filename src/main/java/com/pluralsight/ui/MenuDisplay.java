package com.pluralsight.ui;

import com.pluralsight.drinks.MenuItem;
import com.pluralsight.utillities.*;

import java.time.format.TextStyle;
import java.util.*;

public class MenuDisplay {

    // Category Keywords
    private static final List<String> COFFEE_KEYS = List.of(
            "brew", "milk", "magic", "chilled", "sweet"
    );

    private static final List<String> TEA_KEYS = List.of(
            "tea"
    );

    private static final List<String> SIGNATURE_KEYS = List.of(
            "signature"
    );

    private static final List<String> FOOD_KEYS = List.of(
            "baked", "pastries", "pastry", "scone", "croissant", "food"
    );

    // Keyword match helper
    private static boolean matches(String category, List<String> keys) {
        String cat = category.toLowerCase();
        return keys.stream().anyMatch(cat::contains);
    }


    public static void showCategoryMenu(Scanner read, Map<Integer, MenuItem> menuMap) throws InterruptedException {

        // Category Menu
        System.out.println(Colors.KAPPA_GREEN + "\n--- Browse Categories ---" + Colors.RESET);
        System.out.println("1) Coffee");
        System.out.println("2) Tea");
        System.out.println("3) Signature Drinks");
        System.out.println("4) Food");
        System.out.println("0) Back");
        System.out.print("Choose: ");

        int choice = -1;

        try {
            choice = Integer.parseInt(read.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid entry.");
        }
        Loading.kappaLoader();

        switch (choice) {
            case 1 -> showCoffee(menuMap);
            case 2 -> showTea(menuMap);
            case 3 -> showSignatureDrinks(menuMap);
            case 4 -> showFood(menuMap);
            case 0 -> System.out.println("Returning...");
            default -> System.out.println("Invalid choice.");
        }
    }

    // Display Category
    public static void showCoffee(Map<Integer, MenuItem> menuMap) {
        showCategory("COFFEE", COFFEE_KEYS, menuMap);
    }

    public static void showTea(Map<Integer, MenuItem> menuMap) {
        showCategory("TEA", TEA_KEYS, menuMap);
    }

    public static void showSignatureDrinks(Map<Integer, MenuItem> menuMap) {
        showCategory("SIGNATURE DRINKS", SIGNATURE_KEYS, menuMap);
    }

    public static void showFood(Map<Integer, MenuItem> menuMap) {
        showCategory("FOOD", FOOD_KEYS, menuMap);
    }

   // Display Method
    private static void showCategory(String title, List<String> keywords,
                                     Map<Integer, MenuItem> menuMap) {

        System.out.println("\n----- " + title + " -----");

        boolean found = false;

        for (MenuItem item : menuMap.values()) {

            if (matches(item.getCategory(), keywords)) {
                found = true;

                // For name = color
                String paddedName = String.format("%-25s", item.getName());
                String coloredName = Colors.colorByCategory(paddedName, item.getCategory());

                System.out.printf(
                        "%d) %-25s  Small:$%.2f  Medium:$%.2f  Large:$%.2f%n",
                        item.getItemNumber(),
                        coloredName,
                        item.getPrice("small"),
                        item.getPrice("medium"),
                        item.getPrice("large")
                );
            }
        }

        if (!found) {
            System.out.println("(No items found in this category)");
        }
    }
}

