package com.example.chiffrage.repository;

import com.example.chiffrage.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findByName(String name);

    @Query("SELECT t FROM Task t")
    List<Task> findAllTasks();

    Task findByNameAndId(String name ,Long id);
}
