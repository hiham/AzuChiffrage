package com.example.chiffrage.controllers;

import com.example.chiffrage.dto.UserStoryDTO;
import com.example.chiffrage.entities.UserStory;
import com.example.chiffrage.mapper.UserStoryMapper;
import com.example.chiffrage.services.UserStoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class UserStoryController {

    @Autowired
    private UserStoryMapper userStoryMapper;
    @Autowired
    private final UserStoryService userStoryService;

    @GetMapping("/user-story/{name}")
    public ResponseEntity<UserStoryDTO> getUserStoryByName(@PathVariable  @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{2,}$",message = "Invalid name format [Task's name must be at least 2 characters].") @Valid String name) {
        return new ResponseEntity<>(userStoryMapper.toDTO(userStoryService.getUserStoryByName(name)), HttpStatus.FOUND);
    }

    @PostMapping("/user-story")
    public ResponseEntity<UserStoryDTO> createUserStory(@Valid @RequestBody UserStory userStory) {
        return new ResponseEntity<>(userStoryMapper.toDTO(userStoryService.save(userStory)),HttpStatus.CREATED);
    }

    @GetMapping("/user-stories")
    public ResponseEntity<List<UserStoryDTO>> getUserStoriesByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date enDate) {
        return new ResponseEntity<>(userStoryMapper.findByDatesDTO(userStoryService.getUserStoryiesBetweenDates(startDate, enDate)),HttpStatus.FOUND);
    }
}
