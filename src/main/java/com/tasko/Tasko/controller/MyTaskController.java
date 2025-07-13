package com.tasko.Tasko.controller;

import com.tasko.Tasko.model.Task;
import com.tasko.Tasko.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/my-tasks")
public class MyTaskController {

    private final TaskService taskService;

    public MyTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getMyTasks() {
        return ResponseEntity.ok(taskService.getMyTasks());
    }
}
