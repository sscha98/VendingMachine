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
    private Purchasable candy = new Candy("C1", "Caramel Bar", BigDecimal.valueOf(2.25));
    private Purchasable munchy = new Munchy("C3", "Moonpie", BigDecimal.valueOf(2.95));
    private Purchasable drink = new Drink("B2", "Papsi", BigDecimal.valueOf(3.45));
    private Purchasable gum = new Gum("D3", "Singlemint Gum", BigDecimal.valueOf(2.35));

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
   @Test
    public void getChangeStringDiscount_two_items_of_candy_and_drink(){
        customer.addMoney(new BigDecimal(20));
        customer.addItem(drink);
        customer.addItem(candy);

        String expectedWithDiscount = "Your change is: 15 dollars, 1 quarters, 0 dimes, and 1 nickels. You saved: $1.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void getChangeStringDiscount_four_items_drinks_only(){
        customer.addMoney(new BigDecimal(40));
        customer.addItem(drink);
        customer.addItem(drink);
        customer.addItem(drink);
        customer.addItem(drink);
        String expectedWithDiscount = "Your change is: 28 dollars, 0 quarters, 2 dimes, and 0 nickels. You saved: $2.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void getChangeStringDiscount_six_items_drink_and_candy(){
        customer.addMoney(new BigDecimal(40));
        customer.addItem(drink);
        customer.addItem(drink);
        customer.addItem(drink);
        customer.addItem(candy);
        customer.addItem(candy);
        customer.addItem(candy);

        String expectedWithDiscount = "Your change is: 25 dollars, 3 quarters, 1 dimes, and 1 nickels. You saved: $3.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void getChangeStringDiscount_two_items_drink_and_munchy(){
        customer.addMoney(new BigDecimal(40));
        customer.addItem(drink);
        customer.addItem(munchy);

        String expectedWithDiscount = "Your change is: 34 dollars, 2 quarters, 1 dimes, and 0 nickels. You saved: $1.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void getChangeStringDiscount_two_items_munchy_and_candy(){
        customer.addMoney(new BigDecimal(40));
        customer.addItem(munchy);
        customer.addItem(candy);

        String expectedWithDiscount = "Your change is: 35 dollars, 3 quarters, 0 dimes, and 1 nickels. You saved: $1.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void getChangeStringDiscount_two_items_munchy_and_gum(){
        customer.addMoney(new BigDecimal(10));
        customer.addItem(munchy);
        customer.addItem(gum);

        String expectedWithDiscount = "Your change is: 5 dollars, 2 quarters, 2 dimes, and 0 nickels. You saved: $1.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void getChangeStringDiscount_two_items_drink_and_gum(){
        customer.addMoney(new BigDecimal(10));
        customer.addItem(drink);
        customer.addItem(gum);

        String expectedWithDiscount = "Your change is: 5 dollars, 0 quarters, 2 dimes, and 0 nickels. You saved: $1.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void getChangeStringDiscount_two_items_gum_and_candy(){
        customer.addMoney(new BigDecimal(10));
        customer.addItem(gum);
        customer.addItem(candy);

        String expectedWithDiscount = "Your change is: 6 dollars, 1 quarters, 1 dimes, and 1 nickels. You saved: $1.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void getChangeStringDiscount_four_items_all_types(){
        customer.addMoney(new BigDecimal(40));
        customer.addItem(drink);
        customer.addItem(gum);
        customer.addItem(candy);
        customer.addItem(munchy);

        String expectedWithDiscount = "Your change is: 31 dollars, 0 quarters, 0 dimes, and 0 nickels. You saved: $2.00";
        Assert.assertEquals(expectedWithDiscount, customer.getChangeString());
    }
    @Test
    public void addItem() {
        //Rounding errors in tests
        customer.addMoney(new BigDecimal(10));
        customer.addItem(drink);//3.45
        //adding item subtracts item price from balance
        // every second item invokes discount
        double expected = 6.55;
        Assert.assertEquals(expected, customer.getBalance().doubleValue(), .000009);

        customer.addItem(gum);
        double expectedWithDiscount = 5.20;
        Assert.assertEquals(expectedWithDiscount, customer.getBalance().doubleValue(), .000009);
    }
}