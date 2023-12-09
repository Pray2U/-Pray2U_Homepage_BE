package com.pray2you.p2uhomepage.domain.point.controller;

import com.pray2you.p2uhomepage.domain.point.dto.response.ReadDetailPointResponseDTO;
import com.pray2you.p2uhomepage.domain.point.service.DetailPointService;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DetailPointController {

    private final DetailPointService detailPointService;
    @GetMapping("api/detailpoints/me")
    public ResponseEntity<Map<String, Object>>  readAllMyDetailPoint(Authentication authentication) {
        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        List<ReadDetailPointResponseDTO> responseDTO = detailPointService.readAllMyDetailPoint(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 상세포인트가 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

}
