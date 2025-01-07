package org.example.nbaplayersrater.exceptions;

public class MatchupDoesNotExistException extends RuntimeException {
    public MatchupDoesNotExistException(String message) {
        super(message);
    }

    public MatchupDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
