package com.example.checklist.tasks.domain.models;

public record Task(
        Long idTask,
        String title,
        String description,
        boolean completed,
        boolean erased
        )
{ }
