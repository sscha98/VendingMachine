package com.techelevator.models;

import java.math.BigDecimal;

public interface Purchasable {

    String getMessage();
    String getName();
    BigDecimal getPrice();
    int getNumberInStock();
}
