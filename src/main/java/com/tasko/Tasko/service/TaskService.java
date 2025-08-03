package com.tasko.Tasko.service;

import com.tasko.Tasko.dto.CreateTaskRequest;
import com.tasko.Tasko.dto.TaskDTO;
import com.tasko.Tasko.model.Tag;
import com.tasko.Tasko.model.Task;
import com.tasko.Tasko.model.WorkSpace;
import com.tasko.Tasko.repository.TaskRepository;
import com.tasko.Tasko.repository.WorkSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final WorkSpaceRepository workSpaceRepository;

    public Task createTask(Long workSpaceId, TaskDTO taskDTO) {
        WorkSpace workSpace = workSpaceRepository.findById(workSpaceId).orElseThrow(
                () -> new RuntimeException("Work Space Not Found")
        );

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setWorkSpace(workSpace);
        task.setTags(getTags(taskDTO, task));

        return taskRepository.save(task);
    }

    private List<Tag> getTags(TaskDTO taskDTO, Task task) {
        List<Tag> tags = taskDTO.getTags().stream()
                .map(tag -> {
                    Tag newTag = new Tag();
                    newTag.setTitle(tag.getTitle());
                    newTag.setColor(tag.getColor());
                    newTag.setTask(task);
                    return newTag;
                })
                .toList();
        return tags;
    }

    public List<Task> getTasksByWorkSpace(Long workSpaceId) {
        return taskRepository.findByWorkSpaceId(workSpaceId);
    }

    public List<Task> getMyTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(Long id, TaskDTO taskDTO) {
        Task task = getTaskById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setTags(getTags(taskDTO, task));
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
