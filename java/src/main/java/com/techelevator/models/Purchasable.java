package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Purchasable {
    private final String name;
    private final String slot;
    private final BigDecimal price;
    private int numberInStock=6;

    public Purchasable(String slot, String name, BigDecimal price) {
        this.slot = slot;
        this.name = name;
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

    //when purchase is called, the number in stock subtracts by 1
    public void purchased() {
        numberInStock-=1;
    }

    //Method returning a String message pertaining to each category, item subclasses will override and return specific message
    public String getMessage() {
        return null;
    }
}
