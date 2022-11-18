package com.techelevator.models;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;

public class PurchasableTest extends TestCase {

    private String munchyMessage  = "Munchy, Munchy, so Good!";
    private String candyMessage = "Sugar, Sugar, so Sweet!";
    private String drinkMessage = "Drinky, Drinky, Slurp Slurp!";
    private String gumMessage = "Chewy, Chewy, Lots O Bubbles!";

    private Purchasable candy = new Candy("C1", "Caramel Bar", new BigDecimal(2.25));
    private Purchasable munchy = new Munchy("C3", "Moonpie", new BigDecimal(2.95));
    private Purchasable drink = new Drink("B2", "Papsi", new BigDecimal(3.25));
    private Purchasable gum = new Gum("D3", "Singlemint", new BigDecimal(2.35));

    @Before
    public void setup(){

    }

    @Test
    public void testGetMessage() {
        Assert.assertEquals(candyMessage, candy.getMessage());
        Assert.assertEquals(munchyMessage, munchy.getMessage());
        Assert.assertEquals(drinkMessage, drink.getMessage());
        Assert.assertEquals(gumMessage, gum.getMessage());
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Caramel Bar", candy.getName());
        Assert.assertEquals("Moonpie", munchy.getName());
        Assert.assertEquals("Papsi", drink.getName());
        Assert.assertEquals("Singlemint", gum.getName());
    }

    @Test
    public void testGetPrice() {
        Assert.assertEquals(new BigDecimal(2.25), candy.getPrice());
        Assert.assertEquals(new BigDecimal(2.95), munchy.getPrice());
        Assert.assertEquals(new BigDecimal(3.25), drink.getPrice());
        Assert.assertEquals(new BigDecimal(2.35), gum.getPrice());
    }

    @Test
    public void testGetNumberInStock() {
        int expected = 6;
        Assert.assertEquals(expected, candy.getNumberInStock());
        Assert.assertEquals(expected, munchy.getNumberInStock());
        Assert.assertEquals(expected, drink.getNumberInStock());
        Assert.assertEquals(expected, gum.getNumberInStock());
    }

    @Test
    public void testPurchased() {
        int expected = 5;
        candy.purchased();
        munchy.purchased();
        drink.purchased();
        gum.purchased();
        Assert.assertEquals(expected, candy.getNumberInStock());
        Assert.assertEquals(expected, munchy.getNumberInStock());
        Assert.assertEquals(expected, drink.getNumberInStock());
        Assert.assertEquals(expected, gum.getNumberInStock());

    }
}