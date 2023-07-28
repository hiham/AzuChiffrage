package com.example.chiffrage.services;

import com.example.chiffrage.entities.Resource;
import com.example.chiffrage.exception.DataNotFoundException;
import com.example.chiffrage.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository repository;

    public Resource getResourceByName(String name)
    {
        Optional<Resource> optionalResource = Optional.ofNullable(repository.findByName(name));
        if(!optionalResource.isPresent())
        {
            throw new DataNotFoundException("Resource not found");
        }
        return repository.findByName(name);
    }

    public Resource save(Resource resource)
    {
        return repository.save(resource);
    }
}
