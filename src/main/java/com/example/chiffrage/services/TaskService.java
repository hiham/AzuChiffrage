package com.example.chiffrage.services;

import com.example.chiffrage.entities.Task;
import com.example.chiffrage.exception.DataNotFoundException;
import com.example.chiffrage.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;

    public Task getTaskByName(String name)
    {
        Optional<Task> taskOptional = Optional.ofNullable(taskRepository.findByName(name));
        if(!taskOptional.isPresent())
        {
            throw new DataNotFoundException("Task not found");
        }
        return taskRepository.findByName(name);
    }

    public Task save(Task task)
    {
        return taskRepository.save(task);
    }

    public Task getTaskByNameAndId(String name,Long id)
    {
        Optional<Task> taskOptional = Optional.ofNullable(taskRepository.findByNameAndId(name,id));
        if(!taskOptional.isPresent())
        {
            throw new DataNotFoundException("Task not found");
        }
        return taskRepository.findByNameAndId(name,id);
    }

    public List<Task> getAllTasks()
    {
        return  taskRepository.findAllTasks();
    }
}
