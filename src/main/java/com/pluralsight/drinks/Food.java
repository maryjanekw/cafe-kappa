package com.pluralsight.drinks;

public class Food extends MenuItem{

    // Constructor
    public Food(int itemNumber, String category, String name, double price) {
        super(itemNumber, category, name, price, price, price);
    }

    // Getter
    public double getPrice() {
        return smallPrice;
    }
}
