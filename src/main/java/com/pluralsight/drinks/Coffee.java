package com.pluralsight.drinks;

public class Coffee extends Drink{

    // Constructor
    public Coffee(String size){
        super("", size, 5);
    }


    // Price Calculator
    @Override
    public double calculatedPrice(){
        double sizeMultiplier = switch (size.toLowerCase()){
            case "small" -> 1.0;
            case "medium" -> 1.4;
            case "large" -> 1.6;
            default -> 1.0;
        };
        return basePrice * sizeMultiplier;
    }
}
