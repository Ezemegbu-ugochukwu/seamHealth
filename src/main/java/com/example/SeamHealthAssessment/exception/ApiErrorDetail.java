package com.example.SeamHealthAssessment.exception;

import lombok.*;

import java.util.Date;

@Data
public class ApiErrorDetail {
    private Date timestamp;
    private String message;
    private String details;

    public ApiErrorDetail(String message, String details) {
        this.timestamp = new Date();
        this.message = message;
        this.details = details;
    }
}
