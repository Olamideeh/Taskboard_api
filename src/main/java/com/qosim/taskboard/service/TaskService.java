package com.qosim.taskboard.service;

import com.qosim.taskboard.dto.UpdateTaskRequest;
import com.qosim.taskboard.enity.Task;
import com.qosim.taskboard.exception.TaskNotFound;
import com.qosim.taskboard.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
   // create task
    public Task createTask(UpdateTaskRequest Request) {
        Task task = new Task();

        task.setTaskName(task.getTaskName());
        task.setDescription(task.getDescription());
        task.setPriority(task.getPriority());
        task.setStatus(task.getStatus());

        return taskRepository.save(task);

    }
   // GET ALL
    public List<Task> getAllTasks() {
        return taskRepository.findAll();

    }

    // GET BY ID
    public Task getTaskByID(int taskId) {

        return taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFound(
                                "Task with id " + taskId + " not found"
                        )
                );
    }
    // GET BY NAME
    public Task getTaskByName(String taskName) {
        if (taskName == null || taskName.isBlank()) {
            throw new TaskNotFound("Task name cannot be empty");
        }

        return taskRepository.getTaskByName(taskName)
                .orElseThrow(() ->
                        new TaskNotFound(
                                "Task with name " + taskName + " not found"
                        )
                );
    }

    public void deleteTaskById(int taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFound(
                                "Task with id " + taskId + " not found"
                        )
                );

        taskRepository.delete(task);
    }

    public Task UpdateTaskByID(int taskId, UpdateTaskRequest request) {
        Task task = taskRepository.getTaskById(taskId)
                .orElseThrow(() ->
                        new TaskNotFound("task with id " + taskId + "not found"));


        task.setTaskName(request.getTaskName());
        task.setDescription(request.getDescription());
        task.setPriority(request.getTaskPriority());
        task.setStatus(request.getTaskStatus());

        return taskRepository.save(task);
    }
}