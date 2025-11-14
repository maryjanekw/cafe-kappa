package com.pluralsight.drinks;

public class SignatureDrink extends Drink{

    // Constructor
    public SignatureDrink(int itemNumber, String category, String name,
                          double small, double medium, double large) {
        super(itemNumber, category, name, small, medium, large, "small");
    }
}
