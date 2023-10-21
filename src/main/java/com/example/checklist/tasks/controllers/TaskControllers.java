package com.example.checklist.tasks.controllers;

import com.example.checklist.exceptions.NoTaskInDatabase;
import com.example.checklist.exceptions.NoTasksFound;
import com.example.checklist.exceptions.TaskWithoutTittle;
import com.example.checklist.tasks.entities.TaskEntity;
import com.example.checklist.tasks.repositories.TaskRepository;
import com.example.checklist.tasks.services.TaskServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskControllers {
    public TaskRepository taskRepository;
    public TaskControllers(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @GetMapping("/tasks")
    public ResponseEntity<?> all(int pageNumber) {
        TaskServices taskServices = new TaskServices(taskRepository);
        Pageable userPages = PageRequest.of(pageNumber, 10);
        try {
            Page<TaskEntity> tasks =  taskServices.getAll(userPages);
            return ResponseEntity.ok(tasks);
        } catch (NoTasksFound exception) {
            return ResponseEntity.status(404).body("No tasks found in page");
        } catch (NoTaskInDatabase exception) {
            return ResponseEntity.status(404).body("No tasks found in server");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/tasks")
    public ResponseEntity<?> create(@RequestBody TaskEntity task) {
        TaskServices taskServices = new TaskServices(taskRepository);
        try {
            Long taskCreated = taskServices.create(task);
            return ResponseEntity.ok(taskCreated);
        } catch (TaskWithoutTittle exception) {
            return ResponseEntity.badRequest().body("Task without tittle is not allowed");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Error creating task");
        }
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        TaskServices taskServices = new TaskServices(taskRepository);
        try {
            taskServices.delete(id);
            return ResponseEntity.ok().build();
        } catch (NoTasksFound exception) {
            return ResponseEntity.status(404).body("Task with id " + id + " not found");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Error deleting task");
        }
    }
}
