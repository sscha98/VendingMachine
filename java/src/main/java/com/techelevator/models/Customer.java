package com.techelevator.models;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private BigDecimal amountSaved = new BigDecimal(0);
    private BigDecimal balance=new BigDecimal(0);

    private List<Purchasable> items = new ArrayList<>();
//    private BigDecimal remainingMoney;
//    private BigDecimal itemPrice;
//    private BigDecimal currentBalance;
//    private int numberOfItemsPurchased; //utilize to calc bogodo discount

    private BigDecimal change = new BigDecimal(0);

    public Customer() {}

    public void zeroBalance(){
        balance = new BigDecimal(0);
    }

    public BigDecimal getChange(){
        return change;
    }

    public void addMoney(BigDecimal feedMoney) {
        balance = balance.add(feedMoney);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    //public int getNumberOfItemsPurchased() {return numberOfItemsPurchased;}

//    public BigDecimal getTotalPrice() {
//        BigDecimal sum = BigDecimal.valueOf(0);
//
//        for (Purchasable price : items) {
//            sum = sum.add(price.getPrice());
//        }
//        return sum;
//    }

//    public BigDecimal getRemainingMoney() {
//        BigDecimal discount = new BigDecimal(0);
//        if (items.size() % 2 == 0) {
//            discount = BigDecimal.valueOf(1);
//        }
//        balance = (balance.subtract(itemPrice)).add(discount);
//        return balance;
//    }

    public String getChangeString() {
        int dollars = 0;
        if (balance.compareTo(new BigDecimal(1)) == 1 || balance.compareTo(new BigDecimal(1)) == 0) {
            dollars = balance.intValue();
        }

        BigDecimal moneyProvidedCents = balance.multiply(new BigDecimal(100));
        int cents = moneyProvidedCents.intValue() - 100 * dollars;
        final int QUARTER_VAL = 25;
        final int DIME_VAL = 10;
        final int NICKEL_VAL = 5;

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while (cents > 0) {
            if (cents/QUARTER_VAL > 0) {
                quarters = cents/QUARTER_VAL;
                cents = cents-(quarters*QUARTER_VAL);
            } else if (cents / DIME_VAL > 0) {
                dimes = cents / DIME_VAL;
                cents = cents - (dimes * DIME_VAL);
            } else if (cents / NICKEL_VAL > 0) {
                nickels = cents / NICKEL_VAL;
                cents = cents - (nickels * NICKEL_VAL);
            }
        }
        change = balance;
        zeroBalance();

        return "Your change is: " + dollars + " dollars, " + quarters + " quarters, " + dimes + " dimes, and " +
                nickels + " nickels. You saved: " + NumberFormat.getCurrencyInstance().format(amountSaved);
    }
    public void addItem(Purchasable item){
        items.add(item);
        BigDecimal discount = new BigDecimal(0);
        if (items.size() % 2 == 0) {
            discount = BigDecimal.valueOf(1);
            amountSaved = amountSaved.add(BigDecimal.valueOf(1));
        }
        balance = (balance.subtract(item.getPrice())).add(discount);
    }
}

