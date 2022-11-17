package com.techelevator.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private BigDecimal moneyProvided = BigDecimal.valueOf(0);
    private int numberOfItemsPurchased; //utilize to calc bogodo discount
    private List<Purchasable> items = new ArrayList<>();

    public void addMoney(BigDecimal feedMoney){
        moneyProvided.add(feedMoney);
    }

    public BigDecimal getMoneyProvided() {
        return moneyProvided;
    }

    public int getNumberOfItemsPurchased() {
        return numberOfItemsPurchased;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal discount = BigDecimal.valueOf(items.size() / 2);
        for (Purchasable price : items){
            sum.add(price.getPrice());
        }
        return sum.subtract(discount);
    }

    public BigDecimal getChange(){
        return getMoneyProvided().subtract(getTotalPrice());
    }

    public void addItem(Purchasable item){
        items.add(item);
    }
}
