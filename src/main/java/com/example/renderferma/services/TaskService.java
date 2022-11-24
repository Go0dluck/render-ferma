package com.example.renderferma.services;

import com.example.renderferma.models.Task;
import com.example.renderferma.repositories.TaskRepository;
import com.example.renderferma.util.TaskNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void updateStatus(int id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(value -> {
            value.setStatus("COMPLETE");
            value.setUpdateTask(LocalDateTime.now());
        });
    }

    public Task findById(int id){
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }
}
