package com.example.baam2.controller;

import com.example.baam2.dto.request.AttendanceCreateDTO;
import com.example.baam2.dto.request.SessionCreateDTO;
import com.example.baam2.dto.request.SessionUpdateDTO;
import com.example.baam2.dto.response.AttendanceResponseDTO;
import com.example.baam2.dto.response.SessionResponseDTO;
import com.example.baam2.dto.response.UserAttendanceDTO;
import com.example.baam2.model.UserModel;
import com.example.baam2.repository.SessionRepository;
import com.example.baam2.repository.UserRepository;
import com.example.baam2.service.AttendanceService;
import com.example.baam2.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/create")
    public AttendanceResponseDTO createAttendance(@RequestBody AttendanceCreateDTO attendanceCreateDTO) {
        return attendanceService.createAttendance(attendanceCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }

    @GetMapping("/{id}")
    private List<UserAttendanceDTO> getAllUserAttendance(@PathVariable Long id){
        return attendanceService.getAllUserAttendance(id);
    }

    @GetMapping("/all")
    public List<AttendanceResponseDTO> getAllAttendance(){
        return attendanceService.getAllAttendance();
    }

}