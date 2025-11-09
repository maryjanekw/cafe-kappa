package com.pluralsight.drinks;

public class AddOn {

    private String name;
    private double price;
    private boolean isFree;

    public AddOn(String name, double price, boolean isFree) {
        this.name = name;
        this.price = price;
        this.isFree = isFree;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return isFree ? 0.0 : price;
    }

    public boolean isFree() {
        return isFree;
    }
}
