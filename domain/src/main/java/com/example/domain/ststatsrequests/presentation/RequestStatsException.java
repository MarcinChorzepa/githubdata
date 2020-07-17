package com.example.domain.ststatsrequests.presentation;

public class RequestStatsException extends Exception {

    private static final long serialVersionUID = -8250898664592438461L;

    public RequestStatsException(String message) {
        super(message);
    }

    public RequestStatsException(String message, Throwable cause) {
        super(message,cause);
    }
}
