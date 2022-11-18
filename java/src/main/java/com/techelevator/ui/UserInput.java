package com.techelevator.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.Bidi;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput{

    private static Scanner scanner = new Scanner(System.in);

    public static String getInventoryFile() {
        System.out.print("Please input an inventory file: ");
        String inventoryFile = scanner.nextLine();
        return inventoryFile;
    }

    public static String getHomeScreenOption(){
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");
        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toUpperCase();
        System.out.println();//spacing
        if (option.equals("D")) {
            return "display";
        }
        else if (option.equals("P")){
            return "purchase";
        }
        else if (option.equals("E")){
            return "exit";
        }
        else {
            return "";
        }
    }
    //prints out the purchase menu
    public static String getPurchaseMenuOption(){
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println();
        System.out.print("Please select an option: ");
        //reads the input and stores it as selectedOption
        String selectedOption = scanner.nextLine();
        //the string is then converted to uppercase
        String option = selectedOption.trim().toUpperCase();
        System.out.println();//spacing

        //checks what the purchase menu selection is and returns a relevant string for VendingMachine to read
        if (option.equals("M")){
            return "feed";
        }
        else if (option.equals("S")){
            return "select";
        }
        else if (option.equals("F")){
            return "finish";
        }
        else {
            return "";
        }
    }

    //prompts the user to provide money
    public static BigDecimal getFeedingMoney(){
        System.out.print("Amount of Money you want to Feed in (Whole bills - $1, $5, $10, or $20. No change Accepted): "); //limit user to only entering 1, 5, 10, or 20?
        //stores the user input as an int after parsing
        int selectedOption = Integer.parseInt(scanner.nextLine());
        //returns the BigDecimal value of the money fed
        return BigDecimal.valueOf(selectedOption);
    }

    //prompts the user to make an item selection
    public static String getSelection(){
        System.out.print("\nPlease type in your selection: ");
        //stores the user input as a string
        String selectedOption  = scanner.nextLine();
        //converts the selection into uppercase
        String option = selectedOption.trim().toUpperCase(); //item selection can be lowercase (ex. a3, d1, c2)
        //returns the selection
        return option;
    }
}
