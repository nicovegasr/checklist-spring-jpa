package com.example.checklist.tasks.services;

import com.example.checklist.exceptions.NoTaskInDatabase;
import com.example.checklist.exceptions.NoTasksFound;
import com.example.checklist.exceptions.TaskWithoutTittle;
import com.example.checklist.tasks.entities.TaskEntity;
import com.example.checklist.tasks.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TaskServices {
    protected TaskRepository taskRepository;
    public TaskServices(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Page<TaskEntity> getAll(Pageable userPages) {
        Page<TaskEntity> tasks = taskRepository.findAll(userPages);
        if (tasks.isEmpty() && userPages.getPageNumber() == 0) {
            throw new NoTaskInDatabase();
        }
        if (tasks.isEmpty()) {
            throw new NoTasksFound();
        }
        return tasks;
    }
    public long create(TaskEntity task) {
        if (task.getTitle().isEmpty()) {
            throw new TaskWithoutTittle();
        }
        taskRepository.save(task);
        return task.getIdTask();
    }

    public void delete(Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(NoTasksFound::new);
        taskRepository.delete(task);
    }
}
