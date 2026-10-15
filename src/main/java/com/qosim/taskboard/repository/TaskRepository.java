
package com.qosim.taskboard.repository;
import com.qosim.taskboard.enity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByTaskName(String taskName);
    Optional<Task>getTaskById(Integer taskId);
    Optional<Task>getTaskByName(String taskName);
}
