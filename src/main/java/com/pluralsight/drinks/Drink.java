package com.pluralsight.drinks;

public abstract class Drink extends MenuItem{

    // Variable
    protected String size;

    // Construction
    public Drink(int itemNumber, String category, String name,
                 double smallPrice, double mediumPrice, double largePrice, String size) {
        super(itemNumber, category, name, smallPrice, mediumPrice, largePrice);
        this.size = (size == null || size.isBlank()) ? "small" : size.toLowerCase();
    }

    // Price calculator
    public double calculatedPrice(){
        return getPrice(size);
    }

    // Getter & Setter
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = (size == null || size.isBlank()) ? "small" : size.toLowerCase();
    }

}
