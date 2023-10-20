package com.example.checklist.exceptions;

public class NoTasksFound extends RuntimeException {
    public NoTasksFound() {
        super("No tasks found");
    }
}
