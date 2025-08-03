package com.tasko.Tasko.repository;

import com.tasko.Tasko.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findByTaskId(Long taskId);
}
