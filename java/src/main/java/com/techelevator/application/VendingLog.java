package com.techelevator.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VendingLog {

    public void log(String action, BigDecimal available, BigDecimal remaining){

        File vendingLog = new File ("Audit.txt");

        try (PrintWriter append = new PrintWriter(new FileWriter(vendingLog, true))) {
           //printf("%-22s %-18d %s%f")
            int decimalPlaces = 2;
            available.setScale(decimalPlaces, RoundingMode.CEILING);
            remaining.setScale(decimalPlaces, RoundingMode.CEILING);
            append.printf("%-22s %-18s %-18f %f\n",timeString(), action, available, remaining);
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
