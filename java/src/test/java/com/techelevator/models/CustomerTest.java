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
    private Purchasable drink = new Drink("B2", "Papsi", new BigDecimal(3.25));
    private Purchasable gum = new Gum("D3", "Singlemint", new BigDecimal(2.35));

    @Before
    public void setup(){
        customer = new Customer();
        customer2 = new Customer();
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

   /* @Test
    public void getChangeStringDiscount(){
        customer2.addMoney(new BigDecimal(100));
        customer2.addItem(munchy);
        customer2.getBalance();
        customer2.addItem(candy);
        String expectedWithDiscount = "Your change is: 95 dollars, 3 quarters, 0 dimes, and 1 nickels. You saved: $1.00";
        Assert.assertEquals(expectedWithDiscount, customer2.getChangeString());
    }*/

    @Test
    public void addItem() {

        //Rounding errors in tests
        customer.addMoney(new BigDecimal(10));
        customer.addItem(drink);//3.25
        //adding item subtracts item price from balance
        // every second item invokes discount
        double expected =6.75;
        Assert.assertEquals(expected, customer.getBalance().doubleValue(), .000009);

        customer.addItem(gum);
        double expectedWithDiscount = 5.40;
        Assert.assertEquals(expectedWithDiscount, customer.getBalance().doubleValue(), .000009);
    }
}