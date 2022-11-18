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

    public Customer(){}

    public void addMoney(BigDecimal feedMoney){moneyProvided = moneyProvided.add(feedMoney);}

    public BigDecimal getMoneyProvided() {return moneyProvided;}

    public int getNumberOfItemsPurchased() {return numberOfItemsPurchased;}

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
        final int QUARTER_VAL = 25;
        final int DIME_VAL = 10;
        final int NICKEL_VAL = 5;

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while(cents>0){
<<<<<<< HEAD
            if (cents/quarterVal>0){
                quarters = cents/quarterVal;
                cents = cents -(quarters*quarters);
            }
            else if (cents/dimeVal>0){
                dimes = cents/dimeVal;
                cents = cents - (dimes*dimeVal);
            }
            else if (cents/nickelVal>0) {
                nickels = cents / nickelVal;
                cents = cents - (nickels * nickelVal);
=======
            if (cents/ QUARTER_VAL >0){
                quarters = cents/ QUARTER_VAL;
                cents = cents -(quarters* QUARTER_VAL);
            } else if (cents/ DIME_VAL >0){
                dimes = cents/ DIME_VAL;
                cents = cents - (dimes* DIME_VAL);
            }else if (cents/ NICKEL_VAL >0) {
                nickels = cents / NICKEL_VAL;
                cents = cents - (nickels * NICKEL_VAL);
>>>>>>> 4ddc03d2b396a6ea9e541bbf2b0d1e494fedc71a
            }
        }

        return "Your change is: " + dollars + " dollars, " + quarters + " quarters, " + dimes + " dimes, and " +
                nickels + " nickels";
    }

    public void addItem(Purchasable item){
        items.add(item);
        itemPrice = item.getPrice();
    }

}
