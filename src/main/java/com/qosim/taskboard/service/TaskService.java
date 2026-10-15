package com.qosim.taskboard.service;

import com.qosim.taskboard.dto.UpdateTaskRequest;
import com.qosim.taskboard.enity.Task;
import com.qosim.taskboard.exception.TaskNotFound;
import com.qosim.taskboard.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    public Page<Task> getAllTasks(int page, int size,
                                  String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return taskRepository.findAll(pageable);
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

        return taskRepository.getTaskByName(taskName )
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