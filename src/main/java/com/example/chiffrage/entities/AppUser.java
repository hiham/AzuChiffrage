package com.example.chiffrage.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class AppUser {

    @Id
    @Size(min =  2, max = 20 , message = "User's id must be at least 2 characters and should not exceed 20 characters.")
    @Column(length = 10)
    @NonNull
    @NotNull(message = "Username cannot be null.")
    @NotBlank(message = "Username cannot be blank.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$",message = "Invalid id format [User's name must contains at least one letter and at least one digit].")
    private String username;

    @Size(min =  2, max = 20,message = "User's name must be at least 2 characters." )
    @NotBlank(message = "User's name cannot be blank.")
    @Column(length = 20)
    @Pattern(regexp ="^(?=.*[a-zA-Z])[a-zA-Z[^0-9]]{2,}$",message ="Invalid name format [User's name must contain at least two letters with no digits].")
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min =  2, max = 100 , message = "Password must be at least 2 characters and should not exceed 100 characters.")
    @NonNull
    @NotNull(message = "Password cannot be null.")
    @NotBlank(message = "Password cannot be blank")
    @Column(length = 100)
    private String password;

    @ManyToOne
    private RolePrivilegeMenu rolePrivilegeMenu;
}
