package com.example.chiffrage.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min =  2, max = 20,message = "Resource's name must be at least 2 characters and should not exceed 20 characters.")
    @Column(length = 20, nullable = false)
    @NonNull
    @NotNull(message = "Resource's name cannot be null.")
    @NotBlank(message = "Resource's name cannot be blank.")
    @Pattern(regexp ="^(?=.*[a-zA-Z])[a-zA-Z[^0-9]]{2,}$",message ="Invalid name format [Resource's name must contain at least two letters with no digits]." )
    private String name;

    @Max(value = 80,message = "Total time allocation cannot exceed 80 hours.")
    @Column(precision = 3, scale = 1 )
    private int chargeTotal = 0;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;



}
