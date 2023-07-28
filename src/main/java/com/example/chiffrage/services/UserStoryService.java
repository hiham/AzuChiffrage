package com.example.chiffrage.services;

import com.example.chiffrage.entities.UserStory;
import com.example.chiffrage.exception.DataNotFoundException;
import com.example.chiffrage.repository.UserStoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStoryService {


    private final UserStoryRepository userStoryRepository;

    public UserStory getUserStoryByName(String name)
    {
        Optional<UserStory> userStoryOptional = Optional.ofNullable(userStoryRepository.findByName(name));
        if(!userStoryOptional.isPresent())
        {
            throw new DataNotFoundException("UserStory not found");
        }
        return userStoryRepository.findByName(name);
    }

    public List<UserStory> getUserStoryiesBetweenDates(Date startDate,Date endDate)
    {
        Optional<List<UserStory>> userStoryOptional = Optional.ofNullable(userStoryRepository.findBetweenDates(startDate,endDate));
        if(userStoryOptional.isPresent())
        {
            if(userStoryOptional.get().isEmpty())
            {
                throw new DataNotFoundException("UserStories not found");
            }

        }
        return userStoryRepository.findBetweenDates(startDate,endDate);
    }
    public UserStory save(UserStory userStory){
        return userStoryRepository.save(userStory);
    }

}
