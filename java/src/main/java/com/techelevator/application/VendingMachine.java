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
    public void run(){

        VendingLog logger = new VendingLog();
        while(true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            File menuFileInput = new File("catering.csv");

            Map<String, Purchasable> inventory = new HashMap<>();

            try(Scanner menu = new Scanner(menuFileInput)){
                while(menu.hasNextLine()){
                    String line = menu.nextLine();
                    String[] item = line.split(",");
                    String name = item[0];
                    if (item[3].equals("Munchy")) {
                        inventory.put(name, new Munchy(item[0], item[1], new BigDecimal(item[2])));
                    }
                    else if (item[3].equals("Candy")) {
                        inventory.put(name, new Candy(item[0], item[1], new BigDecimal(item[2])));
                    }
                    else if (item[3].equals("Drink")) {
                        inventory.put(name, new Drink(item[0], item[1], new BigDecimal(item[2])));
                    }
                    else if (item[3].equals("Gum")){
                        inventory.put(name, new Gum(item[0], item[1], new BigDecimal(item[2])));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("That File was not found. Please enter a valid inventory File.");
            }


            if(choice.equals("display")) {
                // display the vending machine slots
                for (Map.Entry<String, Purchasable> entry : inventory.entrySet()){
                    System.out.println(entry.getKey() + "  " + entry.getValue().getName() + "  $" + entry.getValue().getPrice() + "  " + "Number in stock:" + entry.getValue().getNumberInStock());
                }
            }

            else if(choice.equals("purchase")) {
                // make a purchase
                Customer customer = new Customer();
                String purchaseMenuChoice = UserInput.getPurchaseMenuOption();
                while (true) {
                    if (purchaseMenuChoice.equals("feed")) {

                        BigDecimal moneyFed = UserInput.getFeedingMoney();
                        customer.addMoney(moneyFed);
                        System.out.println("\nCurrent Money Provided: $" + customer.getBalance() + "\n");
                        purchaseMenuChoice = UserInput.getPurchaseMenuOption();
                        logger.log("MONEY FED:", "",moneyFed, customer.getBalance());
                    }
                    else if (purchaseMenuChoice.equals("select")) {
                        for (Map.Entry<String, Purchasable> entry : inventory.entrySet()){
                            System.out.println(entry.getKey() + " " + entry.getValue().getName() + " " +
                                    entry.getValue().getPrice() + " " + "Number in stock: " + entry.getValue().getNumberInStock());
                        }
                        String itemSelected = UserInput.getSelection();

                        if (inventory.containsKey(itemSelected)&& inventory.get(itemSelected).getNumberInStock()>0){

                            inventory.get(itemSelected).purchased();
                            BigDecimal preTransaction = customer.getBalance();
                            customer.addItem(inventory.get(itemSelected));
                            System.out.println("\nName: " + inventory.get(itemSelected).getName() + ", Price: $" +
                                    inventory.get(itemSelected).getPrice() + " Money Remaining: $"+ customer.getBalance()+
                                    "\n " + inventory.get(itemSelected).getMessage()+ "\n");
                            logger.log(inventory.get(itemSelected).getName(), itemSelected,
                                    preTransaction, customer.getBalance());

                            purchaseMenuChoice = UserInput.getPurchaseMenuOption();


                        }
                        else if (inventory.containsKey(itemSelected)&& inventory.get(itemSelected).getNumberInStock()==0){
                            System.out.println("NO LONGER AVAILABLE");
                            purchaseMenuChoice = UserInput.getPurchaseMenuOption();
                        }
                        else {
                            System.out.println("Invalid Item");
                            purchaseMenuChoice = UserInput.getPurchaseMenuOption();
                        }
                    }
                    else if (purchaseMenuChoice.equals("finish")) {
                        System.out.println(customer.getChangeString());
                        logger.log("CHANGE GIVEN:","", customer.getChange(), customer.getBalance());

                        break;
                    }
                }
            }
            else if(choice.equals("exit")) {
                // goodbye
                break;
            }
        }
    }
}
