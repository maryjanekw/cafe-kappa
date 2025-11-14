package com.pluralsight.orders;

import com.pluralsight.drinks.*;
import java.util.*;

public class OrderItem {

    // Variables
    private MenuItem menuItem;
    private String size;
    private final List<AddOn> addOns = new ArrayList<>();
    private int quantity = 1;

    // Constructor
    public OrderItem(MenuItem menuItem, String size, List<AddOn> addOns, int quantity) {
        this.menuItem = Objects.requireNonNull(menuItem);
        this.size = (size == null) || size.isBlank() ? "small" : size.trim().toLowerCase();
        if (addOns != null)this.addOns.addAll(addOns);
        this.quantity = Math.max(1, quantity);
    }

    // Getters and Setters

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public String getSize() {
        return size;
    }

    public List<AddOn> getAddOns() {
        return addOns;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(1, quantity);
    }

    // add and remove Add On methods
    public void addAddOns(AddOn addOn){
        if (addOn != null)addOns.add(addOn);
    }

    public void removeAddOn(AddOn addOn){
        addOns.remove(addOn);
    }

    // Calculated total
    public double getLineTotal(){
        double base = menuItem.getPrice(size);
        double addons = addOns.stream().mapToDouble(AddOn::getPrice).sum();
        return (base + addons) * quantity;
    }

    // Formats items for display
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d x %s (%s) - $%.2f", quantity, menuItem.getName(),size, getLineTotal()));
        if (!addOns.isEmpty()){
            sb.append(System.lineSeparator()).append(" Add-ons: ");
            for (AddOn a : addOns) {
                sb.append(a.getName());
                if (!a.isFree()) sb.append(String.format(" ($%.2f)", a.getPrice()));
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
