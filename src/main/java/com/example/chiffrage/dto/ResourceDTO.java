package com.example.chiffrage.dto;

import com.example.chiffrage.entities.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDTO {
    private String name;
    private int chargeTotal = 0;
    @JsonIgnoreProperties({"id","comment","status","type",""})
    private List<Task> taskNames;
}
