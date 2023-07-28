package com.example.chiffrage.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserPrivilege {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "userPrivilege",cascade = CascadeType.ALL)
    private List<RolePrivilegeMenu> rolePrivilegeMenus;


}
