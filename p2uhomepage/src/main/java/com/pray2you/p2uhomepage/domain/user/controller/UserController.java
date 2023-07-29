package com.pray2you.p2uhomepage.domain.user.controller;

import com.pray2you.p2uhomepage.domain.user.dto.request.CreateUserByAdditionalInfoRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRoleRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.response.*;
import com.pray2you.p2uhomepage.domain.user.service.UserService;
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
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users/infoform")
    public ResponseEntity<Map<String, Object>> createUserByAdditionalInfo(
            Authentication authentication,
            @Validated @RequestBody CreateUserByAdditionalInfoRequestDTO requestDTO) {

        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();

        CreateUserByAdditionalInfoResponseDTO responseDTO = userService.createUserByAdditionalInfo(userId, requestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 가입되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/users")
    public ResponseEntity<Map<String, Object>> deleteUser(Authentication authentication){
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        DeleteUserResponseDTO responseDTO = userService.deleteUser(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 탈퇴가 완료되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/api/users")
    public ResponseEntity<Map<String, Object>> updateUser(
            Authentication authentication,
            @RequestBody @Validated UpdateUserRequestDTO requestDTO){
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        UpdateUserResponseDTO responseDTO = userService.updateUser(userId, requestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 수정이 완료되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/users/me")
    public ResponseEntity<Map<String, Object>> readUserInfo(Authentication authentication){
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        ReadUserInfoResponseDTO responseDTO = userService.readUserInfo(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 본인정보 조회가 완료되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/admin/users")
    public ResponseEntity<Map<String, Object>> readAllUser(Pageable pageable) {
        Page<ReadUserInfoResponseDTO> responseDTO = userService.readAllUser(pageable);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "전체 유저 조회가 완료되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("api/admin/users/role/{githubId}")
    public ResponseEntity<Map<String, Object>> updateRole(@PathVariable(value = "githubId") String githubId, @Validated @RequestBody UpdateUserRoleRequestDTO requestDTO) {
        UpdateRoleResponseDTO responseDTO = userService.updateRole(githubId, requestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "해당 유저 권한변경이 완료되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

}
