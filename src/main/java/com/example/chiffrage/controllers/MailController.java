package com.example.chiffrage.controllers;

import com.example.chiffrage.dto.Message;
import com.example.chiffrage.mail.Mail;
import com.example.chiffrage.mail.MailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping(value = "/send")
    public ResponseEntity createMail(@Valid @RequestBody  Mail mail, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors())
        {
            mailService.sendSimpleMessage(mail.getTo(),mail.getSubject(),mail.getText());
            return new ResponseEntity<>(new Message("Mail sent : " + mail), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
}
