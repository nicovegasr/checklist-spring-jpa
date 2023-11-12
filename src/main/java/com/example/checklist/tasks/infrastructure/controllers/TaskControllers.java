package com.example.checklist.tasks.infrastructure.controllers;

import com.example.checklist.tasks.domain.models.Task;
import com.example.checklist.tasks.infrastructure.entities.TaskEntity;
import com.example.checklist.tasks.infrastructure.repositories.MySqlJpaTaskRepositoryAdapter;
import com.example.checklist.tasks.infrastructure.repositories.JpaTaskRepository;
import com.example.checklist.tasks.domain.services.TaskServices;
import com.example.checklist.tasks.infrastructure.responses.TaskResponse;
import com.example.checklist.tasks.infrastructure.responses.TaskResponseCreated;
import com.example.checklist.tasks.infrastructure.responses.TaskResponseGetAll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class TaskControllers {
    public JpaTaskRepository jpaTaskRepository;
    public TaskControllers(JpaTaskRepository jpaTaskRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
    }
    @GetMapping("/tasks")
    public ResponseEntity<TaskResponse> all(@RequestParam(defaultValue = "0") int pageNumber) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        Pageable taskPageRequest = PageRequest.of(pageNumber, 10);
        Page<Task> tasks = taskServices.getAll(taskPageRequest);
        return ResponseEntity.ok(TaskResponseGetAll.builder()
                .timestamp(LocalDateTime.now().toString())
                .statusCode(HttpStatus.OK.value())
                .statusName(HttpStatus.OK.getReasonPhrase())
                .message("Tasks found")
                .tasks(tasks)
                .build());
    }
    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> create(@RequestBody TaskEntity task) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        Long taskCreated = taskServices.create(task.toDomainModel());
        return ResponseEntity.ok(TaskResponseCreated.builder()
                .timestamp(LocalDateTime.now().toString())
                .statusCode(HttpStatus.OK.value())
                .statusName(HttpStatus.OK.getReasonPhrase())
                .message("Task created")
                .id(taskCreated)
                .build());
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> delete(@PathVariable("id") Long id) {
        MySqlJpaTaskRepositoryAdapter mySqlTaskRepositoryAdapter = new MySqlJpaTaskRepositoryAdapter(jpaTaskRepository);
        TaskServices taskServices = new TaskServices(mySqlTaskRepositoryAdapter);
        taskServices.delete(id);
        return ResponseEntity.ok(TaskResponseCreated.builder()
                .timestamp(LocalDateTime.now().toString())
                .statusCode(HttpStatus.OK.value())
                .statusName(HttpStatus.OK.getReasonPhrase())
                .message("Task deleted")
                .build());
    }
}
