package com.pray2you.p2uhomepage.domain.point.controller;

import com.pray2you.p2uhomepage.domain.point.dto.response.ReadCurrentRankResponseDTO;
import com.pray2you.p2uhomepage.domain.point.dto.response.ReadRankResponseDTO;
import com.pray2you.p2uhomepage.domain.point.service.RankService;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@EnableScheduling
public class RankController {

    private final RankService rankService;

    @GetMapping("api/ranks/point/me")
    public ResponseEntity<Map<String, Object>> readCurrentRankPoint(Authentication authentication) {
        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        ReadCurrentRankResponseDTO responseDTO = rankService.readCurrentRankPoint(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 랭크포인트가 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/api/ranks/lastweek/me")
    public ResponseEntity<Map<String, Object>> readPreviousWeekRank(Authentication authentication) {
        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        ReadRankResponseDTO responseDTO = rankService.readPreviousWeekRank(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 지난 주 랭킹이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/ranks")
    public ResponseEntity<Map<String, Object>> readAllPreviousWeekRank(Pageable pageable) {

        Page<ReadRankResponseDTO> responseDTO = rankService.readAllPreviousWeekRank(pageable);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 지난 주 랭킹이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }
}
