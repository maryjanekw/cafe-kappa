package com.pluralsight.drinks;

public abstract class Drink {

    // Variables
    protected String name;
    protected String size;
    protected double basePrice;

    // Construtio
    public Drink(String name, String size, double basePrice) {
        this.name = name;
        this.size = size;
        this.basePrice = basePrice;
    }

    // Abstract price calculator
    public abstract double calculatedPrice();

    // Getters
    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }
}
