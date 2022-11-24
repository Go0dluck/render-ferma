package com.example.renderferma.controllers;

import com.example.renderferma.models.Task;
import com.example.renderferma.services.TaskService;
import com.example.renderferma.util.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Task> updateStatus(@PathVariable(value = "id") int taskId){
        Task task = taskService.findById(taskId);
        taskService.updateStatus(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(TaskNotFoundException e){
        return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
    }
}
