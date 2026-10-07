package com.qosim.taskboard.repository;

import com.qosim.taskboard.enity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> getAllTasks();
    Optional<Task> getTaskById(int taskId);
    Optional<Task> getTaskByName(String taskName);
}
