package com.example.notemanager.users.exception;

public enum ExceptionMessages {
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User with this ID already exists"),
    INVALID_DATA("Invalid user data provided"),
    INVALID_ID("Invalid user id provided");

    private String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
