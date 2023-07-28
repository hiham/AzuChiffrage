package com.example.chiffrage.dto;

import com.example.chiffrage.entities.Resource;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private String name;
    @JsonIgnoreProperties({"id","chargeTotal","tasks"})
    private List<Resource> resourcesName;
}
