package org.example.nbaplayersrater.exceptions;

public class PlayerDidNotParticipateInMatchupException extends IllegalStateException {
    public PlayerDidNotParticipateInMatchupException(String message) {
        super(message);
    }

    public PlayerDidNotParticipateInMatchupException(String message, Throwable cause) {
        super(message, cause);
    }
}
