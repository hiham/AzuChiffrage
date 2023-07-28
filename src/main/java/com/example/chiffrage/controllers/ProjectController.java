package com.example.chiffrage.controllers;

import com.example.chiffrage.dto.ProjectDTO;
import com.example.chiffrage.entities.Project;
import com.example.chiffrage.mapper.ProjectMapper;
import com.example.chiffrage.services.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;

    @PostAuthorize("hasAuthority('write')")
    @GetMapping("/project/{name}")
    public ResponseEntity<ProjectDTO> getProjectByName(@PathVariable @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{2,}$", message = "Invalid name format [Project's name must be at least 2 characters].") @Valid String name)
    {
        return new ResponseEntity<>(projectMapper.toDTO(projectService.getProjectByName(name)), HttpStatus.FOUND);
    }

    @PostMapping("/project")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors())
        {
            return new ResponseEntity<>(projectMapper.toDTO(projectService.save(project)), HttpStatus.CREATED);

        }
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
}
