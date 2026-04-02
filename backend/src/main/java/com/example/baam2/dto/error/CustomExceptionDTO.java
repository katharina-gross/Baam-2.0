package com.example.baam2.dto.error;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CustomExceptionDTO {
    private final String status;
    private final String message;
}
