package com.techelevator.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VendingLog {

    public void log(String action, BigDecimal available, BigDecimal remaining){

        File vendingLog = new File ("Audit.txt");

        try (PrintWriter append = new PrintWriter(new FileWriter(vendingLog, true))) {
           //printf("%-22s %-18d %s%f")
            append.printf("%-15s %-15s %-15s %s\n",timeString(), action,
                    NumberFormat.getCurrencyInstance().format(available),
                    NumberFormat.getCurrencyInstance().format(remaining));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static String timeString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");
        //LocalDateTime time = LocalDateTime.parse(now,formatter);
        return LocalDateTime.now().format(formatter);
    }
}