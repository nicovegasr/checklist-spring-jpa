package com.example.checklist.tasks.services;

import com.example.checklist.exceptions.NoTasksFound;
import com.example.checklist.tasks.entities.TaskEntity;
import com.example.checklist.tasks.repositories.TaskRepository;

import java.util.List;

public class TaskServices {
    protected TaskRepository taskRepository;
    public TaskServices(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<TaskEntity> getAll() {
        List<TaskEntity> tasks = taskRepository.findAll();
        if (tasks.size() == 0) {
            throw new NoTasksFound();
        }
        return tasks;
    }
}
