package com.example.chiffrage.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
    @Email
    @NotNull
    private String to;
    private String recipientName;
    private String subject;
    private String text;
    private String senderName;

    @Override
    public String toString() {
        return "Mail{" +
                "to='" + to + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
