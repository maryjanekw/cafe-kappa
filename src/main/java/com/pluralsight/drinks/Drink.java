package com.pluralsight.drinks;

public abstract class Drink {

    protected String name;
    protected String size;
    protected double basePrice;

    public Drink(String name, String size, double basePrice) {
        this.name = name;
        this.size = size;
        this.basePrice = basePrice;
    }

    public abstract double calculatedPrice();

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }
}
