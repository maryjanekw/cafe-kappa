package com.pluralsight.drinks;

public class Tea extends Drink{

    // Constructor
    public Tea(String size){
        super("", size, 4);
    }


    // Price Calculator
    @Override
    public double calculatedPrice(){
        double sizeMultiplier = switch (size.toLowerCase()){
            case "small" -> 1.0;
            case "medium" -> 1.25;
            case "large" -> 1.5;
            default -> 1.0;
        };
        return basePrice * sizeMultiplier;
    }
}
