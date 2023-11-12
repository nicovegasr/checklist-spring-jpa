package com.example.checklist.tasks.infrastructure.handlers;

import com.example.checklist.tasks.infrastructure.exceptions.TaskResponseError;

import com.example.checklist.tasks.domain.exceptions.NoTaskInDatabase;
import com.example.checklist.tasks.domain.exceptions.NoTasksFound;
import com.example.checklist.tasks.domain.exceptions.TaskWithoutTittle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@ControllerAdvice
@Slf4j
public class TaskErrorSpringHandler {
    private final static DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @ExceptionHandler(NoTaskInDatabase.class)
    public ResponseEntity<TaskResponseError> handleNoTaskInDatabase(NoTaskInDatabase noTaskInDatabaseError) {
        log.error("Error: No task in database", noTaskInDatabaseError);
        TaskResponseError response = TaskResponseError.builder()
                .timestamp(LocalDateTime.now().format(ISO_FORMATTER))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusName(HttpStatus.NOT_FOUND.getReasonPhrase())
                .errorMessage(noTaskInDatabaseError.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoTasksFound.class)
    public ResponseEntity<TaskResponseError> handleNoTasksFound(NoTasksFound noTaskFoundError) {
        log.error("Error: No tasks found", noTaskFoundError);
        TaskResponseError response = TaskResponseError.builder()
                .timestamp(LocalDateTime.now().format(ISO_FORMATTER))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusName(HttpStatus.NOT_FOUND.getReasonPhrase())
                .errorMessage(noTaskFoundError.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskWithoutTittle.class)
    public ResponseEntity<TaskResponseError> handleTaskWithoutTitle(TaskWithoutTittle taskWithoutTittleError) {
        log.error("Error: Title is required", taskWithoutTittleError);
        TaskResponseError response = TaskResponseError.builder()
                .timestamp(LocalDateTime.now().format(ISO_FORMATTER))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusName(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorMessage(taskWithoutTittleError.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TaskResponseError> handleGeneralException(Exception error) {
        log.error("Internal Server Error please contact with an administrator", error);
        TaskResponseError response = TaskResponseError.builder()
                .timestamp(LocalDateTime.now().format(ISO_FORMATTER))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusName(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errorMessage(error.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
