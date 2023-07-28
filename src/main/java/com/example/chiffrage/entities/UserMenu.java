package com.example.chiffrage.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
public class UserMenu {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    private List<RolePrivilegeMenu> rolePrivilegeMenus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMenu userMenu)) return false;
        return Objects.equals(getId(), userMenu.getId()) && Objects.equals(getName(), userMenu.getName()) && Objects.equals(getRolePrivilegeMenus(), userMenu.getRolePrivilegeMenus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRolePrivilegeMenus());
    }
}
