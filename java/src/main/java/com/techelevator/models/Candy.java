package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Purchasable {

    public Candy(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    //String message pertaining to the Candy category
    @Override
    public String getMessage() {
        return "Sugar, Sugar, so Sweet!";
    }
}
