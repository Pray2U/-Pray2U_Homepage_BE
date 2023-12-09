package com.pray2you.p2uhomepage.domain.attendance.controller;

import com.pray2you.p2uhomepage.domain.attendance.dto.response.CreateAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.dto.response.ReadAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.service.AttendanceService;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/api/attendance")
    public ResponseEntity<Map<String, Object>> createAttendance(Authentication authentication) {
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        CreateAttendanceResponseDTO responseDTO = attendanceService.createAttendance(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", LocalDate.now() + " 날짜 출석이 완료되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/attendance/{year}/{month}")
    public ResponseEntity<Map<String, Object>> readAttendance(Authentication authentication, @PathVariable(value = "year") int year, @PathVariable(value = "month") int month) {
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        List<ReadAttendanceResponseDTO> responseDTO = attendanceService.readAttendance(userId, year, month);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", year + "년 "+ month + "월의 출석정보가 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }
}
