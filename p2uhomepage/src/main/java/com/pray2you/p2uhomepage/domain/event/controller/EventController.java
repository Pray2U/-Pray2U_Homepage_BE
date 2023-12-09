package com.pray2you.p2uhomepage.domain.event.controller;

import com.pray2you.p2uhomepage.domain.event.dto.request.CreateEventRequestDTO;
import com.pray2you.p2uhomepage.domain.event.dto.request.UpdateEventRequestDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.CreateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.DeleteEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.ReadEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.UpdateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.service.EventService;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/api/events")
    public ResponseEntity<Map<String, Object>> createEvent(Authentication authentication, @RequestBody @Validated CreateEventRequestDTO requestDTO) {
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        CreateEventResponseDTO responseDTO = eventService.createEvent(userId, requestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "해당 이벤트가 추가되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/api/events")
    public ResponseEntity<Map<String, Object>> updateEvent(Authentication authentication, @RequestBody @Validated UpdateEventRequestDTO requestDTO) {
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        UpdateEventResponseDTO responseDTO = eventService.updateEvent(userId, requestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "해당 이벤트가 수정되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/events/{eventId}")
    public ResponseEntity<Map<String, Object>> deleteEvent(Authentication authentication, @PathVariable(value = "eventId") Long eventId) {
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        DeleteEventResponseDTO responseDTO = eventService.deleteEvent(userId, eventId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "해당 이벤트가 삭제되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/events/{year}/{month}")
    public ResponseEntity<Map<String, Object>> readMonthEvent(@PathVariable(value = "year") int year, @PathVariable(value = "month") int month) {
        List<ReadEventResponseDTO> eventListResponseDTO = eventService.readMonthEvent(year, month);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "이벤트가 조회되었습니다.");
        result.put("data", eventListResponseDTO);
        return ResponseEntity.ok().body(result);
    }
}
