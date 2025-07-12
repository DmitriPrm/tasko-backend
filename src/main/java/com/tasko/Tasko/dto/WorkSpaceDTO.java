package com.tasko.Tasko.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
public class WorkSpaceDTO {
    private Long id;
    private String title;
    private String description;
    private String imagePath;

    @JsonIgnore
    public String getImagePath() {
        return imagePath != null ? getServerBaseUrl() + "/" + imagePath : null;
    }

    private String getServerBaseUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .build()
                .toUriString();
    }
}
