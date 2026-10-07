package com.qosim.taskboard.enity;

import com.qosim.taskboard.enums.TaskPriority;
import com.qosim.taskboard.enums.TaskStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class Task {
    private Long id;
    private String taskName;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
}
