package com.pluralsight.drinks;

public class Tea extends Drink{

    // Constructor
    public Tea(int itemNumber, String category, String name,
               double smallPrice, double mediumPrice, double largePrice, String size) {
        super(itemNumber, category, name, smallPrice, mediumPrice, largePrice, size);
    }

    // Price Calculator
    @Override
    public double calculatedPrice(){
        return switch (size.toLowerCase()){
            case "small" -> smallPrice;
            case "medium" -> mediumPrice;
            case "large" -> largePrice;
            default -> smallPrice;
        };
    }
}
