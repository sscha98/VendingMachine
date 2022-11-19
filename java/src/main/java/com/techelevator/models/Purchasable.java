package com.techelevator.models;

import java.math.BigDecimal;

public interface Purchasable {
    //interface that all the snack items implement
    //they will share all of these methods
    String getMessage();
    String getName();
    BigDecimal getPrice();
    int getNumberInStock();
    void purchased();
}
