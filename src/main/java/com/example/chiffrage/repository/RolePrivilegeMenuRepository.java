package com.example.chiffrage.repository;

import com.example.chiffrage.entities.RolePrivilegeMenu;
import com.example.chiffrage.entities.RolePrivilegeMenuKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePrivilegeMenuRepository extends JpaRepository<RolePrivilegeMenu, RolePrivilegeMenuKey> {
}
