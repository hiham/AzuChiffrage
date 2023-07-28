package com.example.chiffrage.mapper;

import com.example.chiffrage.dto.TaskDTO;
import com.example.chiffrage.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {Task.class})
public interface TaskMapper {

    @Mapping(source = "resource.name", target = "resourceName")
    @Mapping(source = "userStory.name", target = "userStoryName")
    TaskDTO toDTO (Task task);
    List<TaskDTO> findAllDTO(List<Task> tasks);

}
