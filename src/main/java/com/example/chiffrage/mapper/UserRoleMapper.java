package com.example.chiffrage.mapper;

import com.example.chiffrage.dto.UserRoleDTO;
import com.example.chiffrage.entities.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserRole.class})
public interface UserRoleMapper {
    @Mapping(source = "name",target = "name")
    UserRoleDTO toDTO(UserRole userRole);
    List<UserRoleDTO> findAllDTO(List<UserRole> userRoleList);
}
