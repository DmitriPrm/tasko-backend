package com.tasko.Tasko.service;

import com.tasko.Tasko.model.Tag;
import com.tasko.Tasko.repository.TagRepository;
import com.tasko.Tasko.repository.TaskRepository;

import java.util.List;

public class TagService {
    private final TagRepository tagRepository;
    private final TaskRepository taskRepository;

    public TagService(TagRepository tagRepository, TaskRepository taskRepository) {
        this.tagRepository = tagRepository;
        this.taskRepository = taskRepository;
    }

    public List<Tag> getTagsByTaskId(Long taskId) {
        return tagRepository.findByTaskId(taskId);
    }
}
