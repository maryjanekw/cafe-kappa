package com.pluralsight.drinks;

import java.util.ArrayList;
import java.util.List;

public class CustomDrink extends Drink{

    private Drink baseDrink;
    private List<AddOn> addOns = new ArrayList<>();

    public CustomDrink(Drink baseDrink) {
        super(baseDrink.getName(), baseDrink.getSize(), baseDrink.basePrice);
        this.baseDrink = baseDrink;
    }

    public void addAddOn(AddOn addOn){
        addOns.add(addOn);
    }

    @Override
    public double calculatedPrice(){
        double total = baseDrink.calculatedPrice();
        for (AddOn addOn : addOns)
            total += addOn.getPrice();
        return total;
    }
}
