package com.qosim.taskboard.dto;

import com.qosim.taskboard.enums.TaskPriority;
import com.qosim.taskboard.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTaskRequest {

    private Long id;
    @NotBlank(message = "task name is required")
    private String taskName;
    @NotBlank(message = "description is required ")
    private String description;
    @NotNull(message = "input task priority ")
    private TaskPriority taskPriority;
    @NotNull(message = "input task status ")
    private TaskStatus taskStatus;

}
