package com.pluralsight.utillities;

public class Colors {

    // colors
    public static final String RESET = "\u001B[0m";
    public static final String KAPPA_GREEN = "\u001B[38;2;143;206;0m";
    public static final String KAPPA_GOLD = "\u001B[38;2;251;232;109m";
    public static final String KAPPA_BLUE = "\u001B[38;2;95;186;241m";

    // Colors for Names
    public static String colorByCategory(String text, String category) {;
        String cat = category.toLowerCase();

        if (cat.contains("brew") || cat.contains("milk") || cat.contains("chilled") || cat.contains("sweet")
                || cat.contains("tea") || cat.contains("baked"))
            return Colors.KAPPA_BLUE + text + Colors.RESET;
        if (cat.contains("kappa"))
            return Colors.KAPPA_GOLD + text + Colors.RESET;
        return text;
    }
}
