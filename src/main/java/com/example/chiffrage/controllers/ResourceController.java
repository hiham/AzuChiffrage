package com.example.chiffrage.controllers;

import com.example.chiffrage.dto.ResourceDTO;
import com.example.chiffrage.entities.Resource;
import com.example.chiffrage.mapper.ResourceMapper;
import com.example.chiffrage.services.ResourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
public class ResourceController {
    @Autowired
    private final ResourceService resourceService;

    @Autowired
    private ResourceMapper resourceMapper;

    @GetMapping("/resource/{name}")
    public ResponseEntity<ResourceDTO> getResourceByName(@PathVariable @Pattern(regexp ="^(?=.*[a-zA-Z])[a-zA-Z[^0-9]]{2,}$",message ="Invalid name format [Resource's name must contain at least two letters with no digits]." ) @Valid String name)
    {

        return new ResponseEntity<>(resourceMapper.toDTO(resourceService.getResourceByName(name)), HttpStatus.FOUND);
    }

    @PostMapping("/resource")
    public ResponseEntity<?> createResource(@Valid @RequestBody Resource resource, BindingResult bindingResult){
        if(!bindingResult.hasErrors())
        {
            return new ResponseEntity<>(resourceMapper.toDTO(resourceService.save(resource)),HttpStatus.CREATED);

        }
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
}
