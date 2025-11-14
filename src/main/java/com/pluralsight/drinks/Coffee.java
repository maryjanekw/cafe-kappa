package com.pluralsight.drinks;

public class Coffee extends Drink{

    // Constructor
    public Coffee(int itemNumber, String category, String name,
                  double small, double medium, double large) {
        super(itemNumber, category, name, small, medium, large, "small");
    }
}
