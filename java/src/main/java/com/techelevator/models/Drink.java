package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends Purchasable {

    public Drink(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    //String message pertaining to the Drink category
    @Override
    public String getMessage() {
        return "Drinky, Drinky, Slurp Slurp!";
    }
}
