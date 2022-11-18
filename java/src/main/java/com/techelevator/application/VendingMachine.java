package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachine {
    public void run() {

        VendingLog logger = new VendingLog();
        Map<String, Purchasable> inventory = new HashMap<>();

        while (true) {
            File inventoryFile = new File(UserInput.getInventoryFile());
            if (!inventoryFile.exists()) {
                System.out.println("That File was not found. Please enter a valid inventory File.");
                continue;
            }

            try (Scanner menu = new Scanner(inventoryFile)) {
                while (menu.hasNextLine()) {
                    String line = menu.nextLine();
                    String[] item = line.split(",");
                    String name = item[0];
                    if (item[3].equals("Munchy")) {
                        inventory.put(name, new Munchy(item[0], item[1], new BigDecimal(item[2])));
                    } else if (item[3].equals("Candy")) {
                        inventory.put(name, new Candy(item[0], item[1], new BigDecimal(item[2])));
                    } else if (item[3].equals("Drink")) {
                        inventory.put(name, new Drink(item[0], item[1], new BigDecimal(item[2])));
                    } else if (item[3].equals("Gum")) {
                        inventory.put(name, new Gum(item[0], item[1], new BigDecimal(item[2])));
                    }
                }
                break;
            } catch (FileNotFoundException e) {}
        }
        while(true){
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            try{
                if (choice.equals("")){
                    throw new IllegalArgumentException();
                }
            }catch (IllegalArgumentException e){
                System.out.println("\nPlease input valid menu option!\n");
                continue;
            }
            if (choice.equals("display")) {
                // display the vending machine slots
                for (Map.Entry<String, Purchasable> entry : inventory.entrySet()) {
                    System.out.printf("%-5s %-15s $%.2f     Number in Stock:%d \n" ,
                            entry.getKey(),
                            entry.getValue().getName(), entry.getValue().getPrice(),
                            entry.getValue().getNumberInStock());
                }
            } else if (choice.equals("purchase")) {
                // make a purchase
                Customer customer = new Customer();

                while (true) {
                    String purchaseMenuChoice = UserInput.getPurchaseMenuOption();
                    try{
                        if (purchaseMenuChoice.equals("")){
                            throw new IllegalArgumentException();
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println("\nPlease input valid menu option!\n");
                        continue;
                    }
                    if (purchaseMenuChoice.equals("feed")) {

                        BigDecimal moneyFed;
                        try {
                            moneyFed = UserInput.getFeedingMoney();
                            if (!(moneyFed.compareTo(new BigDecimal(1)) == 0 ||
                                    moneyFed.compareTo(new BigDecimal(5)) == 0 ||
                                    moneyFed.compareTo(new BigDecimal(10)) == 0 ||
                                    moneyFed.compareTo(new BigDecimal(20)) == 0)) {
                                throw new WholeDollarException("\nPlease feed whole dollars one at a time!\n");
                            }
                        }catch (NumberFormatException e) {
                            System.out.println("\nYou must enter whole dollars in number format\n");
                            continue;
                        }catch (WholeDollarException e){
                            System.out.println(e.getMessage());
                            continue;
                        }
                        customer.addMoney(moneyFed);
                        System.out.println("\nCurrent Money Provided: " +
                                NumberFormat.getCurrencyInstance().format(customer.getBalance()) + "\n");

                        logger.log("MONEY FED:", "", moneyFed, customer.getBalance());
                    } else if (purchaseMenuChoice.equals("select")) {
                        try{
                            if (customer.getBalance().compareTo(new BigDecimal(0))==0){
                                throw new InsufficientFundsException("\nInsufficient Funds! Please feed money!\n");
                            }
                        }catch(InsufficientFundsException e){
                            System.out.println(e.getMessage());
                            continue;
                        }
                        for (Map.Entry<String, Purchasable> entry : inventory.entrySet()) {

                            System.out.printf("%-5s %-15s  $%.2f     Number in Stock:%d \n" ,
                                    entry.getKey(),
                                    entry.getValue().getName(), entry.getValue().getPrice(),
                                    entry.getValue().getNumberInStock());
                        }
                        String itemSelected = UserInput.getSelection();

                        if (inventory.containsKey(itemSelected) && inventory.get(itemSelected).getNumberInStock() > 0) {
                            try{
                                if (customer.getBalance().compareTo(inventory.get(itemSelected).getPrice())==-1){
                                    throw new InsufficientFundsException("\nInsufficient Funds! Please feed money!\n");
                                }
                            }catch(InsufficientFundsException e){
                                System.out.println(e.getMessage());
                                continue;
                            }
                            inventory.get(itemSelected).purchased();
                            BigDecimal preTransaction = customer.getBalance();
                            customer.addItem(inventory.get(itemSelected));
                            System.out.println("\nName: " + inventory.get(itemSelected).getName() + ", Price: $" +
                                    inventory.get(itemSelected).getPrice() + " Money Remaining: $"
                                    + customer.getBalance());
                            UserOutput.displayMessage(inventory.get(itemSelected).getMessage());


                            logger.log(inventory.get(itemSelected).getName(), itemSelected,
                                    preTransaction, customer.getBalance());

                        } else if (inventory.containsKey(itemSelected)
                                && inventory.get(itemSelected).getNumberInStock() == 0) {
                            System.out.println("NO LONGER AVAILABLE");

                        } else {
                            System.out.println("\nInvalid Item\n");

                        }
                    } else if (purchaseMenuChoice.equals("finish")) {
                        System.out.println(customer.getChangeString());
                        logger.log("CHANGE GIVEN:", "", customer.getChange(), customer.getBalance());

                        break;
                    }
                }
            } else if (choice.equals("exit")) {
                UserOutput.goodBye();
                break;
            }
        }
    }
}
