package com.example.notemanager.notes.exception;

public enum ExceptionMessages {
    NOTE_NOT_FOUND("Note not found"),
    NOTE_ALREADY_EXISTS("Note with this ID already exists"),
    INVALID_NOTE_DATA("Invalid note data provided"),
    INVALID_NOTE_ID("Invalid note id provided");

    private String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}