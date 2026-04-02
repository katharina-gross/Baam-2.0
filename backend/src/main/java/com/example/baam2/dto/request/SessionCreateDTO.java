package com.example.baam2.dto.request;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionCreateDTO {
    private String title;
    private Long ownerId;
}
