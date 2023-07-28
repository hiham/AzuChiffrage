package com.example.chiffrage.dto;

import com.example.chiffrage.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TaskDTO {
    private String comment;
    private String name;
    private Integer originalEstimate;
    private final Type type;
    private Status status;
    private String resourceName;
    private String userStoryName;

}
