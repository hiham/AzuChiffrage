package com.example.chiffrage.repository;

import com.example.chiffrage.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Long> {
    Resource findByName(String name);
}
