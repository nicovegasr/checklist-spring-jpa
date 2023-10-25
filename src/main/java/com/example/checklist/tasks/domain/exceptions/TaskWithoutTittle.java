package com.example.checklist.tasks.domain.exceptions;

public class TaskWithoutTittle extends RuntimeException{
    public TaskWithoutTittle() {
        super("Title is required");
    }
}
