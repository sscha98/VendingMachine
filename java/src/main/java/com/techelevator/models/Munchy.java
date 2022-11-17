package com.techelevator.models;

import java.math.BigDecimal;

public class Munchy implements Purchasable{
    private String name;
    private String slot;
    private BigDecimal price;
    private int numberInStock = 6;

    public Munchy(String slot, String name, BigDecimal price) {
        this.name = name;
        this.slot = slot;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSlot() {
        return slot;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    @Override
    public String getMessage() {
        return "Munchy, Munchy, so Good!";
    }
}
