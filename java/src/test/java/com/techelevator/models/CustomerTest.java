package com.techelevator.models;

import com.techelevator.application.VendingMachine;
import com.techelevator.ui.UserInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer;
    private Customer customer2;
    private Purchasable candy = new Candy("C1", "Caramel Bar", new BigDecimal(2.25));
    private Purchasable munchy = new Munchy("C3", "Moonpie", new BigDecimal(2.95));
    private Purchasable drink = new Drink("B2", "Papsi", new BigDecimal(3.45));
    private Purchasable gum = new Gum("D3", "Singlemint Gum", new BigDecimal(2.35));

    @Before
    public void setup(){
        customer = new Customer();
    }

    @Test
    public void zeroBalance() {
        //adding money to the balance, then zeroing
        customer.addMoney(new BigDecimal(100));
        Assert.assertEquals(new BigDecimal(100), customer.getBalance());
        customer.zeroBalance();
        Assert.assertEquals(new BigDecimal(0), customer.getBalance());
    }

    @Test
    public void getChange() {
        //get change method of Customer class may need tweaking
        customer.addMoney(new BigDecimal(100));
        customer.addItem(candy);
        BigDecimal expected = new BigDecimal(97.75);
        customer.getChangeString();
        Assert.assertEquals(expected, customer.getChange());
    }

    @Test
    public void addMoney() {
        customer.addMoney(new BigDecimal(100));
        Assert.assertEquals(new BigDecimal(100), customer.getBalance());
        customer.addMoney(new BigDecimal(5));
        Assert.assertEquals(new BigDecimal(105), customer.getBalance());
    }


    @Test
    public void getBalance() {
        customer.addMoney(new BigDecimal(100));
        Assert.assertEquals(new BigDecimal(100), customer.getBalance());
    }

    @Test
    public void getChangeString() {
        customer.addMoney(new BigDecimal(100));
        customer.addItem(candy);
        //BigDecimal expected = new BigDecimal(97.75);
        String expected = "Your change is: 97 dollars, 3 quarters, 0 dimes, and 0 nickels. You saved: $0.00";
        Assert.assertEquals(expected, customer.getChangeString());
    }



   /*@Test
    public void getChangeStringDiscountTwoItems(){
        customer.addMoney(new BigDecimal(20));
        customer.addItem(drink);
        customer.addItem(candy);
        String expectedWithDiscount = "Your change is: 15 dollars, 2 quarters, 0 dimes, and 0 nickels. You saved: $1.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }*/

    /*@Test
    public void getChangeStringDiscountFourItems(){
        customer.addMoney(new BigDecimal(40));
        customer.addItem(drink);
        customer.addItem(drink);
        customer.addItem(drink);
        customer.addItem(drink);
        String expectedWithDiscount = "Your change is: 29 dollars, 0 quarters, 0 dimes, and 0 nickels. You saved: $2.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }*/

    /*@Test
    public void getChangeStringDiscountSixItems(){
        customer.addMoney(new BigDecimal(40));
        customer.addItem(drink);
        customer.addItem(drink);
        customer.addItem(drink);
        customer.addItem(candy);
        customer.addItem(candy);
        customer.addItem(candy);
        String expectedWithDiscount = "Your change is: 25 dollars, 3 quarters, 1 dimes, and 1 nickels. You saved: $3.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }*/



    @Test
    public void addItem() {

        //Rounding errors in tests
        customer.addMoney(new BigDecimal(10));
        customer.addItem(drink);//3.45
        //adding item subtracts item price from balance
        // every second item invokes discount
        double expected =6.55;
        Assert.assertEquals(expected, customer.getBalance().doubleValue(), .000009);

        customer.addItem(gum);
        double expectedWithDiscount = 5.20;
        Assert.assertEquals(expectedWithDiscount, customer.getBalance().doubleValue(), .000009);
    }
}