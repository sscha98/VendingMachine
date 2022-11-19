package com.techelevator.models;

import java.math.BigDecimal;

public class Candy implements Purchasable{
    //variables for the Candy class
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

    //when purchase is called, the number in stock subtracts by 1
    @Override
    public void purchased() {
    numberInStock-=1;
    }
    //String message pertaining to the item category
    @Override
    public String getMessage() {
        return "Sugar, Sugar, so Sweet!";
    }
}
