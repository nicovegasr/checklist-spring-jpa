package com.example.checklist.exceptions;

public class NoTaskInDatabase extends RuntimeException {
    public NoTaskInDatabase() { super("No task found in database"); }
}
