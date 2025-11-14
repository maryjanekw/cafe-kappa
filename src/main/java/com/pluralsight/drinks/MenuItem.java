package com.pluralsight.drinks;

public class MenuItem {

    // Variables
    protected int itemNumber;
    protected String category;
    protected String name;

    protected double smallPrice;
    protected double mediumPrice;
    protected double largePrice;

    // Constructor
    public MenuItem(int itemNumber, String category, String name,
                    double smallPrice, double mediumPrice, double largePrice) {
        this.itemNumber = itemNumber;
        this.category = category;
        this.name = name;
        this.smallPrice = smallPrice;
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
    }

    // Getters
    public int getItemNumber() {
        return itemNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice(String size){
        return switch (size.toLowerCase()){
            case "small" -> smallPrice;
            case "medium" -> mediumPrice;
            case "large" -> largePrice;
            default -> smallPrice;
        };
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%d - %s (%s): Small $%.2f | Medium $%.2f | Large $%.2f",
                itemNumber, name, category, smallPrice, mediumPrice, largePrice);
    }
}
