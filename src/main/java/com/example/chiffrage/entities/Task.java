package com.example.chiffrage.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NonNull
    private String comment;

    @Size(min =  2, max = 20,message = "Task's name must be at least 2 characters and should not exceed 20 characters.")
    @Column(length = 20 , nullable = false)
    @NonNull
    @NotBlank(message = "Task's name cannot be blank.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{2,}$",message = "Invalid name format [Task's name must be at least 2 characters].")
    private String name;

    @Max(value = 80,message = "The original estimate must be less than 80 hours.")
    @Column(scale = 1,precision = 3)
    @NonNull
    @NotNull(message = "Original estimate should not be blank")
    private Integer originalEstimate;

    @Column(nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private final Type type;

    @Column(nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade =CascadeType.ALL)
    private Resource resource;

    @ManyToOne(cascade =CascadeType.ALL)
    private UserStory userStory;
}
