package com.example.chiffrage;

import com.example.chiffrage.dto.ProjectDTO;
import com.example.chiffrage.entities.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ProjectMapperTest {
    List<Resource> resources = new ArrayList<>();
    Project project;
    Task task;
    @BeforeEach
    void setUp() {
        Resource[] resources1 = {new Resource("Jean"),new Resource("Daniel"),new Resource("P")};
        resources.addAll(Arrays.asList(resources1));
        project = new Project("EAZURA");
        project.setResources(resources);
        task = new Task(new Long(1),
                "",
                "",
                new Integer(3),
                Type.BACKEND,
                Status.TERMINE,
                new Resource("Da"),
                new UserStory("Daw",project));
    }

    @Test
    public void shouldMapProjectToDtop()
    {
    }
}