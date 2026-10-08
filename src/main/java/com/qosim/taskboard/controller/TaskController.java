package com.qosim.taskboard.controller;

import com.qosim.taskboard.dto.UpdateTaskRequest;
import com.qosim.taskboard.enity.Task;
import com.qosim.taskboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task/")
public class TaskController {
    private TaskService taskService;
    @Autowired
    public TaskController( TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createtask")
    public ResponseEntity<Task> createTask(
            @RequestBody UpdateTaskRequest request) {

        Task savedTask = taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTask);
    }

    @GetMapping("/gettask")
    public ResponseEntity<List<Task>> getAllTask()
    {return ResponseEntity.ok(taskService.getAllTasks());

    }

    @GetMapping("/{id}")
    public ResponseEntity <Task> getTaskById(@PathVariable int id ) {
        Task savedTask = taskService.getTaskByID(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(savedTask);
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
}