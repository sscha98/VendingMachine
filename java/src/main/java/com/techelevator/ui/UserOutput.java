package com.techelevator.ui;

import java.math.BigDecimal;
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
    //prints the goodbye message when the method is called
    public static void goodBye(){
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                    GoodBye!");
        System.out.println("***************************************************");
        System.out.println();
    }
}
