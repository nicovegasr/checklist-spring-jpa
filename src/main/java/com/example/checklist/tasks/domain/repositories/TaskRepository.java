package com.example.checklist.tasks.domain.repositories;

import com.example.checklist.tasks.domain.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepository {
    long save(Task task);
    Page<Task> findAll(Pageable userPages);
    void delete(Task task);
    Task findById(Long id);
}
