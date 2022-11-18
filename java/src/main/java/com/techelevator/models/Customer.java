package com.techelevator.models;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    // private variables
    private BigDecimal amountSaved = new BigDecimal(0);
    private BigDecimal balance=new BigDecimal(0);
    private List<Purchasable> items = new ArrayList<>();
    private BigDecimal change = new BigDecimal(0);
//    private BigDecimal remainingMoney;
//    private BigDecimal itemPrice;
//    private BigDecimal currentBalance;
//    private int numberOfItemsPurchased; //utilize to calc bogodo discount


    //empty constructor
    public Customer() {}
    //zero's out the customer balance when called upon and the change is given
    public void zeroBalance(){
        balance = new BigDecimal(0);
    }
    //returns the change when called upon
    public BigDecimal getChange(){
        return change;
    }
    //adds money to the customer balance when BigDecimal value is passed through the method
    public void addMoney(BigDecimal feedMoney) {
        balance = balance.add(feedMoney);
    }
    //returns the current balance of the customer
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
    //returns the string of the dollar and coin amount of change
    //should only be run when the customer is finished
    public String getChangeString() {
        //declares the dollar amount to be 0
        int dollars = 0;
        // if the balance is greater than or equal to 1
        // dollars is then equal to the balance parsed to an int (rounded down)
        if (balance.compareTo(new BigDecimal(1)) == 1 || balance.compareTo(new BigDecimal(1)) == 0) {
            dollars = balance.intValue();
        }
        //moneyProvidedCents is the balance converted to cents
        BigDecimal moneyProvidedCents = balance.multiply(new BigDecimal(100));
        //cents is the customer balance with the dollar amounts subtracted
        int cents = moneyProvidedCents.intValue() - 100 * dollars;

        //constants of the coin values
        final int QUARTER_VAL = 25;
        final int DIME_VAL = 10;
        final int NICKEL_VAL = 5;

        //declaration of the coin variables
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        //while loop continues until cents = 0
        while (cents > 0) {
            //if the cents divided by coin value is greater than 0
            //the coin count will store the amount of time the coin can be divided from cents
            // then cents is then reassigned to be cents minus the coin count time the coin value
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
        //the change is then reassigned the value of balance
        change = balance;
        //balance is then zeroed
        zeroBalance();

        //returns the string of the number of dollars, quarters, dimes, and nickels with the least amount of coins
        //possible
        return "Your change is: " + dollars + " dollars, " + quarters + " quarters, " + dimes + " dimes, and " +
                nickels + " nickels. You saved: " + NumberFormat.getCurrencyInstance().format(amountSaved);
    }
    //addItem adds the Purchasable item passed through
    //discount is then checked by modulo (every even item purchased is discounted)
    //when an item is added, the item price is then subtracted from the balance . discount is added (0 or 1) depending
    // on the if statement condition
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

