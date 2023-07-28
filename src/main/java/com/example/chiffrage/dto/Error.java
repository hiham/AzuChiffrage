package com.example.chiffrage.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private int code;
    private Date date;
    private String error;
    private String message;
}
