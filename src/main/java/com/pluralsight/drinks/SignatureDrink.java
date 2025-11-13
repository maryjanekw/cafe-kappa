package com.pluralsight.drinks;

public class SignatureDrink extends Drink{

    // Constructor
    public SignatureDrink(int itemNumber, String category, String name,
                          double small, double medium, double large) {
        super(itemNumber, category, name, small, medium, large, "small");
    }

//    // Price Calculator
//    @Override
//    public double calculatedPrice(){
//        return switch (size.toLowerCase()){
//            case "small" -> smallPrice;
//            case "medium" -> mediumPrice;
//            case "large" -> largePrice;
//            default -> smallPrice;
//        };
//    }
}
