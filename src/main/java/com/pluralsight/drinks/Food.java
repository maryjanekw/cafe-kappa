package com.pluralsight.drinks;

public class Food extends MenuItem{

    // Variable
    private double price;

    // Constructor
    public Food(int itemNumber, String category, String name, double price) {
        super(itemNumber, category, name, price, price, price);
        this.price = price;
    }

    // Getter
    public double getPrice() {
        return price;
    }
}
