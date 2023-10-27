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
    public ResponseEntity<?> all(int pageNumber) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        Pageable userPages = PageRequest.of(pageNumber, 10);
        try {
            Page<Task> tasks = taskServices.getAll(userPages);
            return ResponseEntity.ok(tasks);
        } catch (NoTasksFound exception) {
            return ResponseEntity.status(404).body("No tasks found in page");
        } catch (NoTaskInDatabase exception) {
            return ResponseEntity.status(404).body("No tasks found in server");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
    @PostMapping("/tasks")
    public ResponseEntity<?> create(@RequestBody TaskEntity task) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        try {
            Long taskCreated = taskServices.create(task.toDomainModel());
            return ResponseEntity.ok(taskCreated);
        } catch (TaskWithoutTittle exception) {
            return ResponseEntity.badRequest().body("Task without tittle is not allowed");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        try {
            taskServices.delete(id);
            return ResponseEntity.ok().build();
        } catch (NoTasksFound exception) {
            return ResponseEntity.status(404).body("Task with id " + id + " not found");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}