package com.example.renderferma.controllers;

import com.example.renderferma.models.Task;
import com.example.renderferma.models.User;
import com.example.renderferma.services.TaskService;
import com.example.renderferma.services.UserService;
import com.example.renderferma.util.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registrationUser(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/sign-in")
    public ResponseEntity<User> signInUser(@RequestParam("name") String name, @RequestParam("password") String password){
        User returnUser = userService.findByNameAndPassword(name, password);
        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable(value = "id") int userId){
        Task task = new Task(userService.findById(userId));
        taskService.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTasks(@PathVariable(value = "id") int userId){
        return userService.findById(userId).getTaskList();
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(UserNotFoundException e){

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
