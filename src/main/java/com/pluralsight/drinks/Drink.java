package com.pluralsight.drinks;

public abstract class Drink extends MenuItem{

    // Variable
    protected String size;

    // Construction
    public Drink(int itemNumber, String category, String name,
                 double smallPrice, double mediumPrice, double largePrice, String size) {
        super(itemNumber, category, name, smallPrice, mediumPrice, largePrice);
        this.size = size;
    }

    // Abstract price calculator
    public abstract double calculatedPrice();

    // Getters
    public String getSize() {
        return size;
    }

    public double getBasePrice() {
        return getPrice(size);
    }

}
