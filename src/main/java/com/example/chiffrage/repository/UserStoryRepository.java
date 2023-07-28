package com.example.chiffrage.repository;

import com.example.chiffrage.entities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory,Long> {
    UserStory findByName(String name);
    @Query("SELECT u FROM UserStory u WHERE u.date BETWEEN :startDate AND :endDate")
    List<UserStory> findBetweenDates(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

    @Query("SELECT u FROM UserStory u ")
    List<UserStory> getAllUserStory();
}
