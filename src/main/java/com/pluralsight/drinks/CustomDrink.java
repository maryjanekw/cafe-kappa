package com.pluralsight.drinks;

import java.util.ArrayList;
import java.util.List;

public class CustomDrink extends Drink{

    // Variables
    private Drink baseDrink;
    private List<AddOn> addOns = new ArrayList<>();

    // Constructor
    public CustomDrink(Drink baseDrink) {
        super(baseDrink.getItemNumber(), baseDrink.getCategory(), baseDrink.getName(),
                baseDrink.getPrice("small"), baseDrink.getPrice("medium"), baseDrink.getPrice("large"),
                baseDrink.getSize());
        this.baseDrink = baseDrink;
    }



    // Add Add-ons method
    public void addAddOn(AddOn addOn){
        addOns.add(addOn);
    }

    // Price calculator
    @Override
    public double calculatedPrice(){
        double total = baseDrink.calculatedPrice();
        for (AddOn addOn : addOns)
            total += addOn.getPrice();
        return total;
    }

    // String Builder with adding Add-ons
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(baseDrink.getName() + " (" + baseDrink.getSize() + ")");
        if (!addOns.isEmpty()) {
            sb.append(" with ");
            for (int i = 0; i < addOns.size(); i++) {
                sb.append(addOns.get(i).getName());
                if (i < addOns.size() - 1) sb.append(", ");
            }
        }
        sb.append(String.format(" - $%.2f", calculatedPrice()));
        return sb.toString();
    }
}
