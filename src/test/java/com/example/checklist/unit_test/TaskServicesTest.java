package com.example.checklist.unit_test;

import com.example.checklist.tasks.domain.exceptions.NoTaskInDatabase;
import com.example.checklist.tasks.domain.exceptions.NoTasksFound;
import com.example.checklist.tasks.domain.exceptions.TaskWithoutTittle;
import com.example.checklist.tasks.domain.models.Task;
import com.example.checklist.tasks.domain.repositories.TaskRepository;
import com.example.checklist.tasks.domain.services.TaskServices;
import org.junit.jupiter.api.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServicesTest {
    @Nested
    class CreateServiceTest {
        /** Use cases:
         * 1. A task wit all the parameters should be created
         * 2. A task without a title should not be created
         */
        TaskRepository taskRepository;
        TaskServices taskServices;
        @BeforeEach
        void setUp() {
            taskRepository = mock(TaskRepository.class);
            taskServices = new TaskServices(taskRepository);
        }
        @Test
        void shouldCreateTask() {
            // Given
            Task task = new Task(1L, "Task 1", "Description 1", false, false);
            // When
            when(taskRepository.save(task)).thenReturn(task.getIdTask());
            Long taskId = taskServices.create(task);
            // Then
            assertEquals(task.getIdTask(), taskId);
        }
        @Test
        void shouldNotCreateTaskWithoutTitle() {
            // Given
            Task task = new Task(1L, "", "Description 1", false, false);
            // Then
            assertThrows(TaskWithoutTittle.class, () -> taskServices.create(task));
        }
    }
    @Nested
    class DeleteServiceTest {
        /** Use cases:
         * 1. A task should be deleted
         * 2. A task that does not exist should not be deleted
         */
        TaskRepository taskRepository;
        TaskServices taskServices;
        @BeforeEach
        void setUp() {
            taskRepository = mock(TaskRepository.class);
            taskServices = new TaskServices(taskRepository);
        }
        @Test
        void shouldDeleteTask() {
            // Given
            Task task = new Task(1L, "Task 1", "Description 1", false, false);
            // When
            when(taskRepository.findById(task.getIdTask())).thenReturn(task);
            taskServices.delete(task.getIdTask());
            // Then
            verify(taskRepository, times(1)).delete(task);
        }
        @Test
        void shouldNotDeleteTaskThatDoesNotExist() {
            // Given
            Task task = new Task(1L, "Task 1", "Description 1", false, false);
            // Then
            assertThrows(NoTasksFound.class, () -> taskServices.delete(task.getIdTask()));
        }
    }
    @Nested
    class GetServiceTest {
        TaskRepository taskRepository;
        TaskServices taskServices;
        Pageable taskPageRequest;
        Page taskPage;
        @BeforeEach
        public void setUp() {
            taskRepository = mock(TaskRepository.class);
            taskServices = new TaskServices(taskRepository);
            taskPageRequest = PageRequest.of(0, 10);
            taskPage = mock(Page.class);
        }
        @Test
        void shouldGetTasks() {
            // When
            when(taskRepository.findAll(taskPageRequest)).thenReturn(taskPage);
            Page<Task> tasks = taskServices.getAll(taskPageRequest);
            // Then
            assertEquals(taskPage, tasks);
        }
        @Test
        void shouldNotGetTaskWhenThereAreNotTaskInDatabase() {
            // When
            when(taskRepository.findAll(taskPageRequest)).thenReturn(Page.empty());
            // Then
            assertThrows(NoTaskInDatabase.class, () -> taskServices.getAll(taskPageRequest));
        }
        @Test
        void shouldNotGetTaskWhenThereAreNotTaskInPage() {
            // Given
            taskPageRequest = PageRequest.of(1, 10);
            // When
            when(taskRepository.findAll(taskPageRequest)).thenReturn(Page.empty());
            // Then
            assertThrows(NoTasksFound.class, () -> taskServices.getAll(taskPageRequest));
        }
    }
}


