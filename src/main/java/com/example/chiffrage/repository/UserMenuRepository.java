package com.example.chiffrage.repository;

import com.example.chiffrage.entities.UserMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMenuRepository extends JpaRepository<UserMenu,Long> {

    UserMenu findByName(String name);
}
