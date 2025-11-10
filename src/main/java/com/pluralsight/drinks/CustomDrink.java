package com.pluralsight.drinks;

import java.util.ArrayList;
import java.util.List;

public class CustomDrink extends Drink{

    // Variables
    private Drink baseDrink;
    private List<AddOn> addOns = new ArrayList<>();

    // Constructor
    public CustomDrink(Drink baseDrink) {
        super(baseDrink.getName(), baseDrink.getSize(), baseDrink.basePrice);
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
}
