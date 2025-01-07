package org.example.nbaplayersrater.exceptions;

public class PlayerDoesNotExistException extends RuntimeException {
    public PlayerDoesNotExistException(String message) {
        super(message);
    }

    public PlayerDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
