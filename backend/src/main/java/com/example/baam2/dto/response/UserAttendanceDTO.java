package com.example.baam2.dto.response;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAttendanceDTO {
    private Long id;
    private String title;
    private String ownerEmail;
    private LocalDateTime timestamp;
}
