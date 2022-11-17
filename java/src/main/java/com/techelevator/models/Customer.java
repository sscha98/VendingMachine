package com.techelevator.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private BigDecimal moneyProvided = BigDecimal.valueOf(0);
    private int numberOfItemsPurchased; //utilize to calc bogodo discount
    private List<Purchasable> items = new ArrayList<>();
    private BigDecimal remainingMoney;
    private BigDecimal itemPrice;

    public Customer() {
    }

    public void addMoney(BigDecimal feedMoney){
        moneyProvided = moneyProvided.add(feedMoney);
    }

    public BigDecimal getMoneyProvided() {
        return moneyProvided;
    }

    public int getNumberOfItemsPurchased() {
        return numberOfItemsPurchased;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal sum = BigDecimal.valueOf(0);

        for (Purchasable price : items){
            sum = sum.add(price.getPrice());
        }
        return sum;
    }
    public BigDecimal getRemainingMoney(){
        BigDecimal discount = BigDecimal.valueOf(0);
        if (items.size()%2==0){
            discount= BigDecimal.valueOf(1);
        }
        moneyProvided = moneyProvided.subtract(itemPrice).add(discount);
        return moneyProvided;
    }

    public String getChange(){
        Map<String, Integer> change = new HashMap<>();
        int dollars = 0;
        if (moneyProvided.compareTo(new BigDecimal(1))==1 || moneyProvided.compareTo(new BigDecimal(1))==0){
            dollars =moneyProvided.intValue();
        }

        BigDecimal moneyProvidedCents = moneyProvided.multiply(new BigDecimal(100));
        int cents = moneyProvidedCents.intValue()- 100*dollars;
        int quarterVal = 25;
        int dimeVal = 10;
        int nickelVal = 5;

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;


        while(cents>0){
            if (cents/quarterVal>0){
                quarters = cents/quarterVal;
                cents = cents -(quarters*quarters);
            } else if (cents/dimeVal>0){
                dimes = cents/dimeVal;
                cents = cents - (dimes*dimeVal);
            }else if (cents/nickelVal>0) {
                nickels = cents / nickelVal;
                cents = cents - (nickels * nickelVal);
            }

        }


        return "Your change is: " + dollars + " dollars, " + quarters + " quarters, " + dimes + " dimes, " +
                nickels + " nickels";

    }

    public void addItem(Purchasable item){
        items.add(item);
        itemPrice = item.getPrice();

    }
}
