package com.qosim.taskboard.exception;

public class TaskErrorResponse extends RuntimeException {
    public TaskErrorResponse(String message) {
        super(message);
    }
}
