package com.example.checklist.tasks.domain.models;

public class Task {
    protected Long idTask;
    protected String title;
    protected String description;
    protected boolean completed;
    protected boolean erased;

    public Task(Long idTask, String title, String description, boolean completed, boolean erased) {
        this.idTask = idTask;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.erased = erased;
    }
    public Long getIdTask() {
        return idTask;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public boolean isErased() {
        return erased;
    }
}
