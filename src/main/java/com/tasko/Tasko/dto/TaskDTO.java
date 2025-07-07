package com.tasko.Tasko.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime dueDate;
}
