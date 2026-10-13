package com.qosim.taskboard.enity;

import com.qosim.taskboard.enums.TaskPriority;
import com.qosim.taskboard.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class Task {

    private Long id;
    @NotBlank(message = "task name must not be blank")
    private String taskName;
    @NotNull(message = "description must not be empty ")
    private String description;
    @NotBlank(message = "status must not be null")
    private TaskStatus status;
    @NotBlank(message = "priority is required ")
    private TaskPriority priority;
}
