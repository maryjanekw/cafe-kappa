package com.pluralsight.drinks;

public class Tea extends Drink{

    // Constructor
    public Tea(int itemNumber, String category, String name,
               double small, double medium, double large) {
        super(itemNumber, category, name, small, medium, large, "small");
    }
}
