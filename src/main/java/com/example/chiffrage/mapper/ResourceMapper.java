package com.example.chiffrage.mapper;

import com.example.chiffrage.dto.ResourceDTO;
import com.example.chiffrage.entities.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {Resource.class})
public interface ResourceMapper {

    @Mapping(source = "tasks", target = "taskNames")
    ResourceDTO toDTO(Resource resource);

}
