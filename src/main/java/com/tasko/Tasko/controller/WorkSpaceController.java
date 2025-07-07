package com.tasko.Tasko.controller;

import com.tasko.Tasko.dto.WorkSpaceDTO;
import com.tasko.Tasko.model.WorkSpace;
import com.tasko.Tasko.service.WorkSpaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-space")
public class WorkSpaceController {

    private final WorkSpaceService workSpaceService;

    public WorkSpaceController(WorkSpaceService workSpaceService) {
        this.workSpaceService = workSpaceService;
    }

    @PostMapping
    public ResponseEntity<WorkSpace> createWorkSpace(@RequestBody WorkSpaceDTO workSpaceDTO) {
        return ResponseEntity.ok(workSpaceService.createWorkSpace(workSpaceDTO));
    }

    @GetMapping
    public ResponseEntity<List<WorkSpace>> getAll() {
        return ResponseEntity.ok(workSpaceService.getAllWorkSpaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkSpace> getWorkSpaceById(@PathVariable Long id) {
        return ResponseEntity.ok(workSpaceService.getWorkSpaceById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkSpace> updateWorkSpace(@PathVariable Long id, @RequestBody WorkSpaceDTO workSpaceDTO) {
        return ResponseEntity.ok(workSpaceService.updateWorkSpace(id, workSpaceDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<WorkSpace> deleteWorkSpace(@PathVariable Long id) {
        workSpaceService.deleteWorkSpace(id);
        return ResponseEntity.noContent().build();
    }
}
