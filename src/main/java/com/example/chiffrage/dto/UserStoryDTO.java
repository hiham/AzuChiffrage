package com.example.chiffrage.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserStoryDTO {
    private String name;
    private Date date;
    private final String projectName;

}
