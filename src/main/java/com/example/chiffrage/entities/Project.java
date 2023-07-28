package com.example.chiffrage.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2,max = 20,message = "Project's name must be at least 2 characters and should not exceed 20 characters.")
    @Column(length = 20,nullable = false)
    @NonNull
    @NotNull(message = "Project's name cannot be null.")
    @NotBlank(message = "Project's name cannot be blank.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{2,}$", message = "Invalid name format [Project's name must be at least 2 characters].")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UserStory> userStory;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Resource> resources;


}

