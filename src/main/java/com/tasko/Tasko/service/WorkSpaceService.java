package com.tasko.Tasko.service;

import com.tasko.Tasko.dto.WorkSpaceDTO;
import com.tasko.Tasko.model.WorkSpace;
import com.tasko.Tasko.repository.WorkSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkSpaceService {
    private final WorkSpaceRepository workSpaceRepository;

    public WorkSpace createWorkSpace(WorkSpaceDTO workSpaceDTO) {
        WorkSpace workSpace = new WorkSpace();
        workSpace.setId(workSpaceDTO.getId());
        workSpace.setTitle(workSpaceDTO.getTitle());
        workSpace.setDescription(workSpaceDTO.getDescription());
        return workSpaceRepository.save(workSpace);
    }

    public List<WorkSpace> getAllWorkSpaces() {
        return workSpaceRepository.findAll();
    }

    public WorkSpace getWorkSpaceById(Long id) {
        return workSpaceRepository.findById(id).orElseThrow(() -> new RuntimeException("Work Space Not Found"));
    }

    public WorkSpace updateWorkSpace(Long id, WorkSpaceDTO workSpaceDTO) {
        WorkSpace workSpace = getWorkSpaceById(id);
        workSpace.setTitle(workSpaceDTO.getTitle());
        workSpace.setDescription(workSpaceDTO.getDescription());
        return workSpaceRepository.save(workSpace);
    }

    public void deleteWorkSpace(Long id) {
        workSpaceRepository.deleteById(id);
    }
}
