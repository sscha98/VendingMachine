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

        //declares the VendingLog in order to call on the methods for logging data
        VendingLog logger = new VendingLog();
        //initializes a map of inventory with String keys and Purchasable values
        Map<String, Purchasable> inventory = new HashMap<>();

        //while true, loop will continue to run until a break is called
        while (true) {

            //declares a file with a path that is inputted from the getInventoryFile method of UserInput
            File inventoryFile = new File(UserInput.getInventoryFile());

            //Checks if the file exists, and if not, the code loops back over the previous line for the user to input
            //a valid path
            if (!inventoryFile.exists()) {
                UserOutput.fileNotFoundDisplay();
                continue;
            }

            //try with resources to read over the file content
            try (Scanner menu = new Scanner(inventoryFile)) {

                //while the file has lines to read from, the loop will continue
                while (menu.hasNextLine()) {
                    // declares a String line with the data from the line being read
                    // item array then splits the String line using the "," as a regex
                    String line = menu.nextLine();
                    String[] item = line.split(",");
                    String name = item[0];

                    //code determines which item belongs to which class and
                    //  creates new instances of each respective class
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
                //breaks out of the while true loop when the file is finished reading
                break;
            } catch (FileNotFoundException e) {}
        }
        while(true){
            // displays the Home screen output from UserOutput
            UserOutput.displayHomeScreen();
            //asks the user for which option the user wants to select and declares a String variable choice to hold that
            //selection
            String choice = UserInput.getHomeScreenOption();
            //try catch checks to see if the input is valid. UserInput provides else statements to return "" when there
            //is an invalid input
            try{
                if (choice.equals("")){
                    throw new IllegalArgumentException();
                }
            }catch (IllegalArgumentException e){
                //catches the IllegalArgumentException and prompts the user to input a valid option, and loops back
                //over to the home menu
                UserOutput.invalidOptionDisplay();
                continue;
            }
            //if the selection was D, the if code block runs
            if (choice.equals("display")) {
                // display the vending machine slots
                // for each loop goes through elements of the item map and prints out relative information
                for (Map.Entry<String, Purchasable> entry : inventory.entrySet()) {
                    System.out.printf(UserOutput.inventoryFormatting() ,
                            entry.getKey(),
                            entry.getValue().getName(), entry.getValue().getPrice(),
                            entry.getValue().getNumberInStock());
                }
            } else if (choice.equals("purchase")) {
                //when the selection is P
                //this indicates a new customer is ready for purchase and creates a new instance of Customer
                Customer customer = new Customer();
                //while loop will continue to run until break is called
                while (true) {
                    //getPurchaseMenuOption prompts the user to make a selection
                    //String purchaseMenuChoice will store the returned selection
                    String purchaseMenuChoice = UserInput.getPurchaseMenuOption();

                    //try catch checks to see if the input is valid. UserInput provides else statements to return ""
                    // when there is an invalid input
                    try{
                        if (purchaseMenuChoice.equals("")){
                            throw new IllegalArgumentException();
                        }
                    }catch (IllegalArgumentException e){
                        //prompts the user to make input a valid option
                        //continue will then loop back around to the purchase menu
                        //System.out.println("\nPlease input valid menu option!\n");
                        UserOutput.invalidOptionDisplay();
                        continue;
                    }
                    //if feed is selected ("m"), the code will run
                    if (purchaseMenuChoice.equals("feed")) {

                        //initializes moneyFed of BigDecimal type
                        BigDecimal moneyFed;
                        //try catch checks to see if the input is valid.
                        //if a whole dollar value (1, 5, 10, 20) is not inputted
                        //a custom WholeDollarException is thrown
                        //catches are used to catch NumberFormatException (string etc) and outputs a relevant message
                        //WholeDollarException catches the exception thrown and outputs a relevant message
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
                        //when the code passes through the above checks,
                        //the money that is fed is then added to the balance of the customer by the addMoney() method
                        //of the Customer class
                        customer.addMoney(moneyFed);
                        //prints relevant information regarding the current balance in the vending machine
                        UserOutput.currentMoneyProvidedDisplay(customer.getBalance());
                        //log() of the VendingLog Class appends this message to the Audit.txt to indicate
                        //Money had been fed into the machine
                        logger.log("MONEY FED:", "", moneyFed, customer.getBalance());
                    } else if (purchaseMenuChoice.equals("select")) {
                        //if the selection is select ("s") the try catch checks if there is a balance in the
                        //vending machine. if not, the custom exception  InsufficientFundsException is thrown.
                        try{
                            if (customer.getBalance().compareTo(new BigDecimal(0))==0){
                                throw new InsufficientFundsException("\nInsufficient Funds! Please feed money!\n");
                            }
                        }catch(InsufficientFundsException e){
                            System.out.println(e.getMessage());
                            continue;
                        }
                        //when the "s" is inputted and there is at least some funds in the vending machine
                        // the vending machine will output the item map to display the slot value, name, price, and stock
                        for (Map.Entry<String, Purchasable> entry : inventory.entrySet()) {
                            System.out.printf(UserOutput.inventoryFormatting() ,
                                    entry.getKey(),
                                    entry.getValue().getName(), entry.getValue().getPrice(),
                                    entry.getValue().getNumberInStock());
                        }
                        //getSelection from the UserInput class is then called and prompts the user to make a selection
                        //based on the slot values and String itemSelected stores the returned selection
                        String itemSelected = UserInput.getSelection();
                        //if the item map contains the slot value and the inventory of that value is greater than 0
                        //the code block runs to the try catch to check if the balance is great enough to purchase
                        //the selected item, if not the InsufficientFundsException is thrown
                        if (inventory.containsKey(itemSelected) && inventory.get(itemSelected).getNumberInStock() > 0) {
                            try{
                                if (customer.getBalance().compareTo(inventory.get(itemSelected).getPrice())==-1){
                                    throw new InsufficientFundsException("\nInsufficient Funds! Please feed money!\n");
                                }
                            }catch(InsufficientFundsException e){
                                System.out.println(e.getMessage());
                                continue;
                            }
                            //once the exceptions are handled
                            // the Purchasable item is then "purchased" by calling the purchased() method
                            //which lowers the numberInStock value
                            inventory.get(itemSelected).purchased();
                            //preTransaction stores the balance before the transaction is made for logging
                            BigDecimal preTransaction = customer.getBalance();
                            //the item is then added to item list of customer
                            // when a purchasable item goes through the .add() method
                            //the balance of the customer subtracts the item price depending on whether there is a
                            //discount of not
                            customer.addItem(inventory.get(itemSelected));
                            //prints out the name and price of the item selected and the customer's balance
                            UserOutput.userSelectionDisplay(inventory.get(itemSelected).getName(),
                                    inventory.get(itemSelected).getPrice(), customer.getBalance());
                            //displays the relevant message for the item category
                            UserOutput.displayMessage(inventory.get(itemSelected).getMessage());
                            //logs the item selection, prior balance, and new balance
                            logger.log(inventory.get(itemSelected).getName(), itemSelected,
                                    preTransaction, customer.getBalance());

                        } else if (inventory.containsKey(itemSelected)
                                && inventory.get(itemSelected).getNumberInStock() == 0) {
                            //checks to see whether the item is in stock
                            UserOutput.noLongerAvailable();
                        } else {
                            //prints when an Invalid item is selected
                            UserOutput.invalidItem();
                        }
                    } else if (purchaseMenuChoice.equals("finish")) {
                        //runs the getChangeString() method in the Customer Class and prints the relevant info
                        System.out.println(customer.getChangeString());
                        //logs that the change was given, the prior balance (change), and the new balance (0)
                        logger.log("CHANGE GIVEN:", "", customer.getChange(), customer.getBalance());
                        break;
                    }
                }
            } else if (choice.equals("exit")) {
                //when e is selected in the home menu, the code exits and prints a goodbye message
                UserOutput.goodBye();
                break;
            }
        }
    }
}
