package com.example.chiffrage.mapper;

import com.example.chiffrage.dto.ProjectDTO;
import com.example.chiffrage.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {Project.class})
public interface ProjectMapper {

    @Mapping(source ="resources",target = "resourcesName")
    ProjectDTO toDTO(Project project);
}
