package com.example.chiffrage.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
public class UserRole {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min =  2, max = 20,message = "Role's name must be at least 2 characters and should not exceed 20 characters.")
    @NotBlank(message = "Role's name cannot be blank.")
    @Pattern(regexp = "^(USER|ADMIN)$",message = "Invalid role format [Role must be either USER or ADMIN].")
    @Column(length = 20)
    private String name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<RolePrivilegeMenu> rolePrivilegeMenus;

}
