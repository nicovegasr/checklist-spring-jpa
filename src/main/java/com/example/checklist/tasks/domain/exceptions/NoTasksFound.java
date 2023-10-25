package com.example.checklist.tasks.domain.exceptions;

public class NoTasksFound extends RuntimeException {
    public NoTasksFound() {
        super("No tasks found");
    }
}
