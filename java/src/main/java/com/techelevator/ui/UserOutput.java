package com.techelevator.ui;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput {
    //prints a message that passes through the method
    public static void displayMessage(String message){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void inventoryFileDisplay(){
        System.out.print("Please input an inventory file: ");
    }

    public static void fileNotFoundDisplay(){
        System.out.println("That File was not found. Please enter a valid inventory File.");
    }

    //prints the home screen when the method is called
    public static void displayHomeScreen(){
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
        System.out.println("Thanksgiving Sale! Every 2nd item purchased is $1 off!");
        System.out.println();
    }

    //displays home screen option
    public static void homeScreenOptions(){
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");
        System.out.println();
        System.out.print("Please select an option: ");
    }
    public static void purchaseMenuOptions(){
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println();
        System.out.print("Please select an option: ");
    }
    //formats the inventory display
    public static String inventoryFormatting(){
        return "%-5s %-15s  $%.2f     In Stock: %d \n";
    }
    // displays the prompt for feeding money
    public static void feedingMoneyDisplay(){
        System.out.print("Amount of Money you want to Feed in (Whole bills - $1, $5, $10, or $20. No change Accepted): ");
    }
    //displays the current balance in the vending machine
    public static void currentMoneyProvidedDisplay(BigDecimal balance ){
        System.out.println("\nCurrent Money Provided: " +
                NumberFormat.getCurrencyInstance().format(balance) + "\n");
    }
    // displays invalid input prompt
    public static void invalidOptionDisplay(){
        System.out.println("\nPlease input valid menu option!\n");
    }
    //displays the item selection
    public static void userSelectionDisplay(String name, BigDecimal price, BigDecimal balance){
        System.out.println("\nName: " + name + ", Price: $" +
                                    price + " Money Remaining: $"
                                    + balance);
    }
    //displays the select prompt
    public static void selectingDisplay(){
        System.out.print("\nPlease type in your selection: ");
    }
    // displays the no longer available prompt
    public static void noLongerAvailable(){
        System.out.println("\nNO LONGER AVAILABLE\n");
    }
    // displays the invalid item prompt
    public static void invalidItem(){
        System.out.println("\nInvalid Item\n");
    }
    //displays the change given
    public static String changeGivenDisplay(int dollars, int quarters, int dimes, int nickels, BigDecimal amountSaved){
        return "Your change is: " + dollars + " dollars, " + quarters + " quarters, " + dimes + " dimes, and " +
                nickels + " nickels. You saved: " + NumberFormat.getCurrencyInstance().format(amountSaved);

    }

    //prints the goodbye message when the method is called
    public static void goodBye(){
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                    GoodBye!");
        System.out.println("***************************************************");
        System.out.println();
    }
}
