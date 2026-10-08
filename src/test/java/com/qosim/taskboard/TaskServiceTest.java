package com.qosim.taskboard;
import com.qosim.taskboard.dto.UpdateTaskRequest;
import com.qosim.taskboard.enity.Task;
import com.qosim.taskboard.enums.TaskPriority;
import com.qosim.taskboard.enums.TaskStatus;
import com.qosim.taskboard.repository.TaskRepository;
import com.qosim.taskboard.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;


    // =========================
    // CREATE TASK
    // =========================

    @Test
    void shouldCreateTask() {

        UpdateTaskRequest request = new UpdateTaskRequest();

        request.setTaskName("Learn Spring Boot");
        request.setDescription("Practice testing");
        request.setPriority("HIGH");
        request.setStatus("TODO");

        Task savedTask = new Task();

        savedTask.setId(1L);
        savedTask.setTaskName("Learn Spring Boot");
        savedTask.setDescription("Practice testing");
        savedTask.setPriority(TaskPriority.valueOf("HIGH"));
        savedTask.setStatus(TaskStatus.valueOf("TODO"));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(savedTask);

        Task result = taskService.createTask(request);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Learn Spring Boot", result.getTaskName());

        verify(taskRepository, times(1))
                .save(any(Task.class));
    }


    // =========================
    // GET ALL TASKS
    // =========================

    @Test
    void shouldGetAllTasks() {

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTaskName("Learn Spring Boot");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTaskName("Learn JUnit");

        List<Task> tasks = List.of(task1, task2);

        when(taskRepository.findAll())
                .thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Learn Spring Boot",
                result.get(0).getTaskName());

        verify(taskRepository, times(1))
                .findAll();
    }


    // =========================
    // GET TASK BY ID
    // =========================

    @Test
    void shouldGetTaskById() {

        Task task = new Task();

        task.setId(1L);
        task.setTaskName("Learn Spring Boot");

        when(taskRepository.findById(1))
                .thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Learn Spring Boot",
                result.getTaskName());

        verify(taskRepository, times(1))
                .findById(1);
    }


    // =========================
    // GET TASK BY ID - NOT FOUND
    // =========================

    @Test
    void shouldThrowExceptionWhenTaskIdDoesNotExist() {

        when(taskRepository.findById(99))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> taskService.getTaskById(99)
        );

        assertEquals(
                "Task with id 99 not found",
                exception.getMessage()
        );

        verify(taskRepository, times(1))
                .findById(99);
    }


    // =========================
    // GET TASK BY NAME
    // =========================

    @Test
    void shouldGetTaskByName() {

        Task task = new Task();

        task.setId(1);
        task.setTaskName("Learn Spring Boot");

        when(taskRepository.getTaskByName("Learn Spring Boot"))
                .thenReturn(Optional.of(task));

        Task result =
                taskService.getTaskByName("Learn Spring Boot");

        assertNotNull(result);
        assertEquals("Learn Spring Boot",
                result.getTaskName());

        verify(taskRepository, times(1))
                .getTaskByName("Learn Spring Boot");
    }


    // =========================
    // GET TASK BY NAME - EMPTY
    // =========================

    @Test
    void shouldThrowExceptionWhenTaskNameIsEmpty() {

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> taskService.getTaskByName("")
        );

        assertEquals(
                "Task name cannot be empty",
                exception.getMessage()
        );

        verify(taskRepository, never())
                .getTaskByName(anyString());
    }


    // =========================
    // GET TASK BY NAME - NOT FOUND
    // =========================

    @Test
    void shouldThrowExceptionWhenTaskNameDoesNotExist() {

        when(taskRepository.getTaskByName("Unknown"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> taskService.getTaskByName("Unknown")
        );

        assertEquals(
                "Task with name Unknown not found",
                exception.getMessage()
        );

        verify(taskRepository, times(1))
                .getTaskByName("Unknown");
    }


    // =========================
    // DELETE TASK
    // =========================

    @Test
    void shouldDeleteTask() {

        Task task = new Task();

        task.setId(1L);
        task.setTaskName("Learn Spring Boot");

        when(taskRepository.findById(1))
                .thenReturn(Optional.of(task));

        taskService.deleteTaskById(1);

        verify(taskRepository, times(1))
                .findById(1);

        verify(taskRepository, times(1))
                .delete(task);
    }


    // =========================
    // DELETE TASK - NOT FOUND
    // =========================

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingTask() {

        when(taskRepository.findById(99))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> taskService.deleteTaskById(99)
        );

        assertEquals(
                "Task with id 99 not found",
                exception.getMessage()
        );

        verify(taskRepository, never())
                .delete(any(Task.class));
    }
}
