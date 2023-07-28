package com.example.chiffrage.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2,max = 20,message = "UserStory's name must be at least 2 characters and should not exceed 20 characters.")
    @Column(length = 20,nullable = false)
    @NonNull
    @NotBlank(message = "UserStory's name cannot be blank.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{2,}$",message = "Invalid name format[Task's name must be at least 2 characters].")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private Date date;

    @ManyToOne
    private final Project project;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> taskList;

}
