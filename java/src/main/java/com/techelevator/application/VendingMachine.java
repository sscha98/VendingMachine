package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine 
{
    public void run()
    {
        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            File menuFileInput = new File("catering.csv");

            //List<Purchasable> inventory = new ArrayList<>();
            Map<String, Purchasable> inventory = new HashMap<>();

            try(Scanner menu = new Scanner(menuFileInput)){
                while(menu.hasNextLine()){
                    String line = menu.nextLine();
                    String[] item = line.split(",");
                    String name = item[0];
                    if (item[3].equals("Munchy")) {
                        inventory.put(name, new Munchy(item[0], item[1], new BigDecimal(item[2])));
                    } else if (item[3].equals("Candy")) {
                        inventory.put(name, new Candy(item[0], item[1], new BigDecimal(item[2])));
                    } else if (item[3].equals("Drink")) {
                        inventory.put(name, new Drink(item[0], item[1], new BigDecimal(item[2])));
                    } else if (item[3].equals("Gum")){
                        inventory.put(name, new Gum(item[0], item[1], new BigDecimal(item[2])));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }


            if(choice.equals("display")) {
                // display the vending machine slots
                for (Map.Entry<String, Purchasable> entry : inventory.entrySet()){
                    System.out.println(entry.getKey() + " " + entry.getValue().getName() + " " + entry.getValue().getPrice() + " " + "Number in stock: " + entry.getValue().getNumberInStock());
                }
            }
            else if(choice.equals("purchase")) {
                // make a purchase
                Customer customer = new Customer();
                String purchaseMenuChoice = UserInput.getPurchaseMenuOption();
                do {
                    if (purchaseMenuChoice.equals("feed")) {
                        String moneyFed = UserInput.getFeedingMoney();
                        System.out.println("Current Money Provided: " + moneyFed);

                    } else if (purchaseMenuChoice.equals("select")) {

                    } else if (purchaseMenuChoice.equals("finish")) {
                    }
                }
                    while (!purchaseMenuChoice.equals("finish"));

                }

            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }
    }
    
}
