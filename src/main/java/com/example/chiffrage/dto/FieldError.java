package com.example.chiffrage.dto;

import lombok.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldError {
    private int code;
    private Date date;
    private String error;
    private String message;
    private Map<String,String> fieldErrors;

    public static Map<String,String> toMessage(String message) {

        String[] errorMessageArray = message.split(", ");
        Map<String, String> errorMap = new HashMap<>();
        for (String errorMessage : errorMessageArray) {
            String[] parts = errorMessage.split(": ");
            if (parts.length > 1) {
                String key = parts[0].substring(parts[0].lastIndexOf('.') + 1);
                String value = parts[1];
                if (errorMap.containsKey(key)) {
                    int count = 1;
                    String newKey;
                    do {
                        newKey = key + "_" + count++;
                    } while (errorMap.containsKey(newKey));
                    key = newKey;
                }
                errorMap.put(key, value);
            }
        }
        return errorMap;
    }
}
