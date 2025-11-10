package com.pluralsight.drinks;

public class SignatureDrink extends Drink{

    public SignatureDrink(String size){
        super("", size, 6);
    }

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
