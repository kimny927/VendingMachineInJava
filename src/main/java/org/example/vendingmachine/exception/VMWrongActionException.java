package org.example.vendingmachine.exception;

public class VMWrongActionException extends RuntimeException {
    public VMWrongActionException(String message){
        super(message);
    }

    public VMWrongActionException(String message, Exception e) {
        super(message, e);
    }
}
