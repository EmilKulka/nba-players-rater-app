package org.example.nbaplayersrater.exceptions;

public class MatchupAlreadyAnsweredException extends IllegalStateException {
    public MatchupAlreadyAnsweredException(String message) {
        super(message);
    }

    public MatchupAlreadyAnsweredException(String message, Throwable cause) {
        super(message, cause);
    }
}
