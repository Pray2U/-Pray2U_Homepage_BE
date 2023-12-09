package com.pray2you.p2uhomepage.domain.til.controller;

import com.pray2you.p2uhomepage.domain.til.dto.request.CreateTilRequestDTO;
import com.pray2you.p2uhomepage.domain.til.dto.request.UpdateTilRequestDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.CreateTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.DeleteTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.ReadTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.UpdateTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.service.TilService;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TilController {

    private final TilService tilService;

    @PostMapping("api/tils")
    ResponseEntity<Map<String, Object>> createTil(Authentication authentication,@Validated @RequestBody CreateTilRequestDTO requestDTO) {
        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        CreateTilResponseDTO responseDTO = tilService.createTil(userId, requestDTO);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 TIL이 생성되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("api/tils/{tilId}")
    ResponseEntity<Map<String, Object>> updateTil(Authentication authentication, @PathVariable long tilId,@Validated @RequestBody UpdateTilRequestDTO requestDTO) {
        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        UpdateTilResponseDTO responseDTO = tilService.updateTil(userId, tilId, requestDTO);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 TIL이 수정되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("api/tils/{tilId}")
    ResponseEntity<Map<String, Object>> deleteTil(Authentication authentication, @PathVariable long tilId) {
        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        DeleteTilResponseDTO responseDTO = tilService.deleteTil(userId, tilId);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 TIL이 삭제되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/tils/{tilId}")
    ResponseEntity<Map<String, Object>> readTil(@PathVariable long tilId) {

        ReadTilResponseDTO responseDTO = tilService.readTil(tilId);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 TIL이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/users/{userId}/tils")
    ResponseEntity<Map<String, Object>> readUserTil(Pageable pageable, @PathVariable long userId){
        Page<ReadTilResponseDTO> responseDTOPage = tilService.readUserTil(pageable, userId);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 userId :" + userId + "의 TIL이 조회되었습니다.");
        result.put("data", responseDTOPage);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/tils")
    ResponseEntity<Map<String, Object>> readTils(Pageable pageable, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<ReadTilResponseDTO> responseDTOPage = tilService.readTils(pageable, keyword);

        Map<String, Object> result = new HashMap<>();

        keyword = keyword.isEmpty() ?  "전체" : "search = " + keyword;
        result.put("msg", "성공적으로 " + keyword + " TIL이 조회되었습니다.");
        result.put("data", responseDTOPage);
        return ResponseEntity.ok().body(result);
    }
}
