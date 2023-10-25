package com.example.checklist.tasks.domain.services;

import com.example.checklist.tasks.domain.exceptions.NoTaskInDatabase;
import com.example.checklist.tasks.domain.exceptions.TaskWithoutTittle;
import com.example.checklist.tasks.domain.exceptions.NoTasksFound;
import com.example.checklist.tasks.domain.models.Task;
import com.example.checklist.tasks.domain.repositories.TaskRepository;
import com.example.checklist.tasks.infrastructure.entities.TaskEntity;
import com.example.checklist.tasks.infrastructure.repositories.JpaTaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class TaskServices {
    protected TaskRepository taskRepository;
    public TaskServices(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Page<Task> getAll(Pageable userPages) {
        Page<Task> tasks = taskRepository.findAll(userPages);
        if (tasks.isEmpty() && userPages.getPageNumber() == 0) {
            throw new NoTaskInDatabase();
        }
        if (tasks.isEmpty()) {
            throw new NoTasksFound();
        }
        return tasks;
    }
    public long create(Task task) {
        if (task.getTitle().isEmpty()) {
            throw new TaskWithoutTittle();
        }
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new NoTasksFound();
        }
        taskRepository.delete(task);
    }
}
