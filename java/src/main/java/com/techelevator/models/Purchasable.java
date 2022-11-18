package com.techelevator.models;

import java.math.BigDecimal;

public interface Purchasable {
    //interface that all of the snack items implements
    //they will share all of these methods
    String getMessage();
    String getName();
    BigDecimal getPrice();
    int getNumberInStock();
    void purchased();
}
