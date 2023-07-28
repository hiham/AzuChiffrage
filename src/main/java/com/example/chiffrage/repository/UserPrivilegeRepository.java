package com.example.chiffrage.repository;

import com.example.chiffrage.entities.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege,Long > {
    UserPrivilege findByName(String name);
}
