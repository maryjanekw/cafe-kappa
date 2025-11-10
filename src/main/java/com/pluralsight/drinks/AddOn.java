package com.pluralsight.drinks;

public class AddOn {

    // Variables
    private String name;
    private double price;
    private boolean isFree;

    // Constructor
    public AddOn(String name, double price, boolean isFree) {
        this.name = name;
        this.price = price;
        this.isFree = isFree;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return isFree ? 0.0 : price; // isFree = false, price = 0.75
    }

    public boolean isFree() {
        return isFree;
    }
}
