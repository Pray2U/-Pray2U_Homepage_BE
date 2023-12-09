package com.pray2you.p2uhomepage.domain.point.controller;

import com.pray2you.p2uhomepage.domain.point.dto.response.ReadTotalPointResponseDTO;
import com.pray2you.p2uhomepage.domain.point.service.TotalPointService;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TotalPointController {

    private final TotalPointService totalPointService;

    @GetMapping("api/points/me")
    ResponseEntity<Map<String, Object>> readMyPoint(Authentication authentication) {

        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        ReadTotalPointResponseDTO responseDTO = totalPointService.readTotalPoint(userId);

        Map<String , Object> result = new HashMap<>();
        result.put("msg","포인트 조회에 성공했습니다.");
        result.put("data", responseDTO);

        return ResponseEntity.ok().body(result);
    }
}
