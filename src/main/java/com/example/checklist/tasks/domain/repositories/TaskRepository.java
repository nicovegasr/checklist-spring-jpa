package com.example.checklist.tasks.domain.repositories;

import com.example.checklist.tasks.domain.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    public long save(Task task);
    public Page<Task> findAll(Pageable userPages);
    public void delete(Task task);
    public Task findById(Long id);
}
