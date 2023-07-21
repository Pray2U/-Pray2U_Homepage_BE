package com.pray2you.p2uhomepage.domain.memberapproval.controller;

import com.pray2you.p2uhomepage.domain.memberapproval.dto.request.CreateMemberApprovalRequestDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.CreateMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.DeleteMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.ReadMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.service.MemberApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApprovalController {

    private final MemberApprovalService memberApprovalService;

    @PostMapping("/api/admin/member-approvals")
    public ResponseEntity<Map<String, Object>> createMemberApprovals(
            @Validated @RequestBody CreateMemberApprovalRequestDTO createMemberApprovalRequestDTO) {
        CreateMemberApprovalResponseDTO responseDTO = memberApprovalService.createMemberApprovals(createMemberApprovalRequestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "해당 멤버가 승인되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/admin/member-approvals/{githubId}")
    public ResponseEntity<Map<String, Object>> deleteMemberApprovals(@PathVariable(value = "githubId") String githubId) {
        DeleteMemberApprovalResponseDTO responseDTO = memberApprovalService.deleteMemberApprovals(githubId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "해당 멤버가 삭제되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/admin/member-approvals")
    public ResponseEntity<Map<String, Object>> getAllMemberApprovals(Pageable pageable){
        Page<ReadMemberApprovalResponseDTO> memberApprovals = memberApprovalService.getAllMemberApprovals(pageable);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "전체 승인멤버가 조회되었습니다.");
        result.put("data", memberApprovals);
        return ResponseEntity.ok().body(result);
    }
}
