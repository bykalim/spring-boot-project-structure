package org.example.advancedcrud.app.exception;

public class NoSuchMovieException extends RuntimeException{
    public NoSuchMovieException() {
        super("No such movie found");
    }
}
