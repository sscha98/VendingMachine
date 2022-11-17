package com.techelevator.models;

import java.math.BigDecimal;

public class Candy implements Purchasable{
    private String name;
    private String slot;
    private BigDecimal price;
    private int numberInStock = 6;

    public Candy(String slot, String name, BigDecimal price) {
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
    public void purchased() {
    numberInStock-=1;
    }

    @Override
    public String getMessage() {
        return "Sugar, Sugar, so Sweet!";
    }
}
