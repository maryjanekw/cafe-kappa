package com.pluralsight.drinks;

public class AddOn {

    // Variables
    private int itemNumber;
    private String category;
    private String name;
    private double price;
    private boolean isFree;

    // Constructor
    public AddOn(int itemNumber, String category, String name, double price, boolean isFree) {
        this.itemNumber = itemNumber;
        this.category = category;
        this.name = name;
        this.price = price;
        this.isFree = isFree;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isFree() {
        return isFree;
    }
}
