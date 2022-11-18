package com.techelevator.ui;

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

    public static String getPurchaseMenuOption(){
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toUpperCase();
        System.out.println();//spacing

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

    public static BigDecimal getFeedingMoney(){
        System.out.print("Amount of Money you want to Feed in (Whole bills - $1, $5, $10, or $20. No change Accepted):  "); //limit user to only entering 1, 5, 10, or 20?
        int selectedOption = Integer.parseInt(scanner.nextLine());

        return BigDecimal.valueOf(selectedOption);
    }

    public static String getSelection(){
        System.out.print("\nPlease type in your selection: ");
        String selectedOption  = scanner.nextLine();
        String option = selectedOption.trim().toUpperCase(); //item selection can be lowercase (ex. a3, d1, c2)
        return option;
    }
}
