package com.qosim.taskboard.dto;

import com.qosim.taskboard.enums.TaskPriority;
import com.qosim.taskboard.enums.TaskStatus;

public class UpdateTaskRequest {

    private Long id;
    private String TaskName;
    private String description;
    TaskPriority taskPriority;
    TaskStatus taskStatus;

}
