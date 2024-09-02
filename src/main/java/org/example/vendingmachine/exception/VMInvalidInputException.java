package org.example.vendingmachine.exception;

public class VMInvalidInputException extends RuntimeException {
    public VMInvalidInputException(String message) {
        super(message);
    }

    public VMInvalidInputException(String message, Exception e) {
        super(message, e);
    }
}
