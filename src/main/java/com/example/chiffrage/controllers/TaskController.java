package com.example.chiffrage.controllers;

import com.example.chiffrage.dto.TaskDTO;
import com.example.chiffrage.entities.Task;
import com.example.chiffrage.mapper.TaskMapper;
import com.example.chiffrage.services.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class TaskController {

    @Autowired
    private final TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("/task/{name}")
    public ResponseEntity<TaskDTO> getTaskByName(@PathVariable @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{2,}$",message = "Invalid name format [Task's name must be at least 2 characters].") @Valid String name)
    {
        return new ResponseEntity<>(taskMapper.toDTO(taskService.getTaskByName(name)),HttpStatus.FOUND);
    }

    @PostMapping("/task")
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors())
        {
            return new ResponseEntity<>(taskMapper.toDTO(taskService.save(task)), HttpStatus.CREATED);

        }
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks()
    {
        return new ResponseEntity<>(taskMapper.findAllDTO(taskService.getAllTasks()),HttpStatus.FOUND);
    }

}
