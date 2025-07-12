package com.tasko.Tasko.controller;

import com.tasko.Tasko.dto.TaskDTO;
import com.tasko.Tasko.model.Task;
import com.tasko.Tasko.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-space/{workSpaceId}/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> create(@PathVariable Long workSpaceId, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(workSpaceId, taskDTO));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasksByWorkSpaceId(@PathVariable Long workSpaceId) {
        return ResponseEntity.ok(taskService.getTasksByWorkSpace(workSpaceId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
