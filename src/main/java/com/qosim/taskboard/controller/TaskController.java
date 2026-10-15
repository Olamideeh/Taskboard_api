package com.qosim.taskboard.controller;

import com.qosim.taskboard.dto.UpdateTaskRequest;
import com.qosim.taskboard.enity.Task;
import com.qosim.taskboard.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task/")
public class TaskController {
    private TaskService taskService;
    @Autowired
    public TaskController( TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createtask")
    public ResponseEntity<Task> createTask(@Valid
            @RequestBody UpdateTaskRequest request) {

        Task savedTask = taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTask);
    }

    @GetMapping("/gettask")
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                taskService.getAllTasks(page, size, sortBy, direction)
        );
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<Task> getTaskByName(@PathVariable String name) {
        return ResponseEntity.ok(taskService.getTaskByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById( @PathVariable int id) {
        taskService.deleteTaskById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask( @PathVariable int id, @Valid @RequestBody UpdateTaskRequest request) {
        Task task = taskService.UpdateTaskByID(id,request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(task);
    }
}