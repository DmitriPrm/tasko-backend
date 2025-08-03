package com.tasko.Tasko.dto;

import com.tasko.Tasko.model.Tag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateTaskRequest {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private List<Tag> tags;
}
