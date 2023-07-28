package com.example.chiffrage.controllers;

import com.example.chiffrage.dto.Error;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class IndexController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error error() {
        return new Error(HttpStatus.NOT_FOUND.value(),new Date(),HttpStatus.NOT_FOUND.name(),"Path not found");
    }

}