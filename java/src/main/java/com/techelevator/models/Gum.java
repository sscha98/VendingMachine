package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends Purchasable {

    public Gum(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    //String message pertaining to the Gum category
    @Override
    public String getMessage() {
        return "Chewy, Chewy, Lots O Bubbles!";
    }
}
