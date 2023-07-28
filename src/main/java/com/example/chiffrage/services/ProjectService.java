package com.example.chiffrage.services;

import com.example.chiffrage.entities.Project;
import com.example.chiffrage.exception.DataNotFoundException;
import com.example.chiffrage.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project getProjectByName(String name)
    {
        Optional<Project> optionalProject = Optional.ofNullable(projectRepository.findByName(name));
        if(!optionalProject.isPresent())
        {
            throw new DataNotFoundException("Project not found");
        }
        return projectRepository.findByName(name);
    }

    public Project save(Project project)
    {
        return projectRepository.save(project);
    }
}
