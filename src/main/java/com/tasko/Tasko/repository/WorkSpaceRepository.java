package com.tasko.Tasko.repository;

import com.tasko.Tasko.model.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> { }
