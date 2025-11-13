package com.pluralsight.orders;

import com.pluralsight.drinks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {

    // Variables
    private final String orderId;
    private final LocalDateTime timeCreated;
    private final List<OrderItem> lines;
    private double taxRate = 0.0725;  // tax = 7.25%


    // Constructor
    public Order(double taxRate) {
        this.orderId = UUID.randomUUID().toString();
        this.timeCreated = LocalDateTime.now();
        this.lines = new ArrayList<>();
        this.taxRate = taxRate;
    }

    // No-Arg Constructor
    public Order(){
        this(0.0725);
    }

    // Getters
    public String getOrderId() {

        return orderId;
    }

    public LocalDateTime getTimeCreated() {

        return timeCreated;
    }

    public List<OrderItem> getLines() {

        return List.copyOf(lines);
    }

    // Line adding method
    public void addLine (OrderItem line){
        if (line == null){
            throw new IllegalArgumentException("Order cannot be completed");
        }
        lines.add(line);
    }

    // Order is Empty
    public boolean isEmpty(){
        return lines.isEmpty();
    }

    // Add new item to order
    public void addMenuItem(MenuItem menuItem, String size, List<AddOn> addOns, int quantity){
        OrderItem line = new OrderItem(menuItem, size, addOns, quantity);
        addLine(line);
    }

    // Remove item from order
    public boolean removeLine(OrderItem line) {
        return lines.remove(line);
    }

    // Total of order
    public double calculateTotal(){
        return lines.stream()
                .mapToDouble(OrderItem::getLineTotal)
                .sum();
    }

    public double getSubTotal(){
        return lines.stream()
                .mapToDouble(OrderItem::getLineTotal)
                .sum();
    }

    public double getTax(){
        return getSubTotal() * taxRate;
    }

    public double getTotal(){
        return getSubTotal() + getTax();
    }

    // Receipt Format
    public String buildReceiptText(){
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        sb.append("====== Café Kappa Receipt ======\n");
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Date: ").append(timeCreated.format(formatter)).append("\n\n");

        for (OrderItem line : lines){
            sb.append(line.toString()).append("\n");
        }

        sb.append("\nSubtotal: $").append(String.format("%.2f", getSubTotal())).append("\n");
        sb.append(String.format("Tax (%.1f%%): $%.2f\n", taxRate * 100, getTax()));
        sb.append(String.format("Total: $%.2f\n", getTotal()));
        sb.append("\nThank you for visiting Café Kappa!\n");

        return sb.toString();
    }


}
