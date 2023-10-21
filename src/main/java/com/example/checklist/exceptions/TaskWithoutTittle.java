package com.example.checklist.exceptions;

public class TaskWithoutTittle extends RuntimeException{
    public TaskWithoutTittle() {
        super("Title is required");
    }
}
