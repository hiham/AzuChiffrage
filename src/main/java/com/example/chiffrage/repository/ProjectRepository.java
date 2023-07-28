package com.example.chiffrage.repository;

import com.example.chiffrage.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByName(String name);
}
