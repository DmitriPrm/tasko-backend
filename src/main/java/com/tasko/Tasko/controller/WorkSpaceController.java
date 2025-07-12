package com.tasko.Tasko.controller;

import com.tasko.Tasko.dto.WorkSpaceDTO;
import com.tasko.Tasko.model.WorkSpace;
import com.tasko.Tasko.service.WorkSpaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/work-space")
public class WorkSpaceController {

    private final WorkSpaceService workSpaceService;

    public WorkSpaceController(WorkSpaceService workSpaceService) {
        this.workSpaceService = workSpaceService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WorkSpace> createWorkSpace(
            @RequestPart("title") String title,
            @RequestPart("description") String description,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        WorkSpaceDTO workSpaceDTO = new WorkSpaceDTO();
        workSpaceDTO.setTitle(title);
        workSpaceDTO.setDescription(description);

        if(image != null && !image.isEmpty()) {
            try {
                if(!Objects.requireNonNull(image.getContentType()).startsWith("image/")) {
                    return ResponseEntity.badRequest().build();
                }
                Path uploadDir = Paths.get("uploads/workspaces");

                if(!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();

                Path filePath = uploadDir.resolve(fileName);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                workSpaceDTO.setImagePath("uploads/workspaces/" + fileName);

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

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
