package com.example.chiffrage.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@IdClass(RolePrivilegeMenuKey.class)
public class RolePrivilegeMenu implements Serializable {
    @Id
    private UserRole role;

    @Id
    private UserPrivilege userPrivilege;

    @Id
    private UserMenu menu;

}
