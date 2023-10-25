package com.example.checklist.tasks.domain.exceptions;

public class NoTaskInDatabase extends RuntimeException {
    public NoTaskInDatabase() { super("No task found in database"); }
}
