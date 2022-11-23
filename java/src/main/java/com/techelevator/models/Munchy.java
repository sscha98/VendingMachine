package com.techelevator.models;

import java.math.BigDecimal;

public class Munchy extends Purchasable {

    public Munchy(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    //String message pertaining to the Munchy category
    @Override
    public String getMessage() {
        return "Munchy, Munchy, so Good!";
    }
}
