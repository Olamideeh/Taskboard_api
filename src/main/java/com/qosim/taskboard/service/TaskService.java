package com.qosim.taskboard.service;

import com.qosim.taskboard.dto.UpdateTaskRequest;
import com.qosim.taskboard.enity.Task;
import com.qosim.taskboard.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                        new RuntimeException(
                                "Task with id " + taskId + " not found"
                        )
                );
    }
    // GET BY NAME
    public Task getTaskByName(String taskName) {
        if (taskName == null || taskName.isBlank()) {
            throw new RuntimeException("Task name cannot be empty");
        }

        return taskRepository.getTaskByName(taskName)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Task with name " + taskName + " not found"
                        )
                );
    }

    public void deleteTaskById(int taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Task with id " + taskId + " not found"
                        )
                );

        taskRepository.delete(task);
    }
}