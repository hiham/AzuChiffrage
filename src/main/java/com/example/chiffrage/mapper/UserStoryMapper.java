package com.example.chiffrage.mapper;

import com.example.chiffrage.dto.UserStoryDTO;
import com.example.chiffrage.entities.UserStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserStory.class})
public interface UserStoryMapper {

    @Mapping(source = "project.name", target = "projectName")
    UserStoryDTO toDTO(UserStory userStory);
    List<UserStoryDTO> findByDatesDTO(List<UserStory> userStories);
}
