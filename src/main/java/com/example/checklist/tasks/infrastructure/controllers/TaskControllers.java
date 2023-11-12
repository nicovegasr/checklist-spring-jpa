package com.example.checklist.tasks.infrastructure.controllers;

import com.example.checklist.tasks.domain.exceptions.NoTaskInDatabase;
import com.example.checklist.tasks.domain.exceptions.NoTasksFound;
import com.example.checklist.tasks.domain.exceptions.TaskWithoutTittle;
import com.example.checklist.tasks.domain.models.Task;
import com.example.checklist.tasks.infrastructure.entities.TaskEntity;
import com.example.checklist.tasks.infrastructure.repositories.MySqlJpaTaskRepositoryAdapter;
import com.example.checklist.tasks.infrastructure.repositories.JpaTaskRepository;
import com.example.checklist.tasks.domain.services.TaskServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskControllers {
    public JpaTaskRepository jpaTaskRepository;
    public TaskControllers(JpaTaskRepository jpaTaskRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
    }
    @GetMapping("/tasks")
    public ResponseEntity<?> all(@RequestParam(defaultValue = "0") int pageNumber) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        Pageable taskPageRequest = PageRequest.of(pageNumber, 10);
        Page<Task> tasks = taskServices.getAll(taskPageRequest);
        return ResponseEntity.ok(tasks);
    }
    @PostMapping("/tasks")
    public ResponseEntity<?> create(@RequestBody TaskEntity task) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        Long taskCreated = taskServices.create(task.toDomainModel());
        return ResponseEntity.ok(taskCreated);

    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        taskServices.delete(id);
        return ResponseEntity.ok().build();
    }
}
