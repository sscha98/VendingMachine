package com.techelevator.models;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException() {super();}

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
