package com.example.chiffrage.repository;

import com.example.chiffrage.entities.AppUser;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {
    Optional<AppUser> findByUsernameIgnoreCase(String login);

    Optional<AppUser> findByName(String name);

    @Override
    <S extends AppUser> @NonNull S save(@NonNull S entity);
}
