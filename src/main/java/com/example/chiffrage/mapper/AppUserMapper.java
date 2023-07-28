package com.example.chiffrage.mapper;

import com.example.chiffrage.dto.AppUserDTO;
import com.example.chiffrage.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {AppUser.class})
public interface AppUserMapper {
    @Mapping(source = "rolePrivilegeMenu.role.name", target = "roleName")
    AppUserDTO toDTO(AppUser appUser);
    List<AppUserDTO> findAllDTO(List<AppUser> appUsers);
}
