package org.example.advancedcrud.app.exception;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException() {
        super("No such user found");
    }
}
