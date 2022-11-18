package com.techelevator.models;

public class WholeDollarException extends Exception{
    public WholeDollarException() {super();}

    public WholeDollarException(String message) {
        super(message);
    }

    public WholeDollarException(String message, Throwable cause) {
        super(message, cause);
    }
}
