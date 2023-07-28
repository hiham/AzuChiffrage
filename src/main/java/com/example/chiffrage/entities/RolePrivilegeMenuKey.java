package com.example.chiffrage.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class RolePrivilegeMenuKey implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private UserRole role;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "privilege_id")
    private UserPrivilege userPrivilege;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private UserMenu menu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RolePrivilegeMenuKey that)) return false;
        return Objects.equals(getRole(), that.getRole()) && Objects.equals(getUserPrivilege(), that.getUserPrivilege()) && Objects.equals(getMenu(), that.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), getUserPrivilege(), getMenu());
    }
}
