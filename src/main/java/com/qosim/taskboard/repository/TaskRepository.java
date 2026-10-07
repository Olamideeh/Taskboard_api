package com.qosim.taskboard.repository;

import com.qosim.taskboard.dto.TaskRequest;
import com.qosim.taskboard.dto.UpdateTaskRequest;
import com.qosim.taskboard.enity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    UpdateTaskRequest createTask (TaskRequest request,Task task);
    List<Task> getAllTasks();
    Optional<Task> getTaskById(int taskId);
    Optional<Task> getTaskByName(String name);
    Task deleteTaskById(int taskId);



}
