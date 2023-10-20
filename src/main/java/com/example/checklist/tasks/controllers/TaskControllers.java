package com.example.checklist.tasks.controllers;

import com.example.checklist.exceptions.NoTasksFound;
import com.example.checklist.tasks.entities.TaskEntity;
import com.example.checklist.tasks.repositories.TaskRepository;
import com.example.checklist.tasks.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskControllers {
    @Autowired
    public TaskRepository taskRepository;
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> all() {
        TaskServices taskServices = new TaskServices(taskRepository);
        try {
            List<TaskEntity> tasks =  taskServices.getAll();
            return ResponseEntity.ok(tasks);
        } catch (NoTasksFound exception) {
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
