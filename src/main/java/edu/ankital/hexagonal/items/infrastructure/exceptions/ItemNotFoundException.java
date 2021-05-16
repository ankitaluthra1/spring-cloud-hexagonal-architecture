package edu.ankital.hexagonal.items.infrastructure.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message){
        super(message);
    }
}
