package com.pray2you.p2uhomepage.domain.item.controller;

import com.pray2you.p2uhomepage.domain.item.dto.request.CreateItemRequestDTO;
import com.pray2you.p2uhomepage.domain.item.dto.request.UpdateItemRequestDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.CreateItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.DeleteItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.ReadItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.UpdateItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/api/sale-items")
    public ResponseEntity<Map<String, Object>> createItem(@RequestBody @Validated CreateItemRequestDTO requestDTO) {

        CreateItemResponseDTO responseDTO = itemService.createItem(requestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "아이템이 생성되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/sale-items/{itemId}")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable Long itemId) {
        DeleteItemResponseDTO responseDTO = itemService.deleteItem(itemId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "아이템이 삭제되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/api/sale-items/{itemId}")
    public ResponseEntity<Map<String, Object>> updateItem(@PathVariable Long itemId, @RequestBody @Validated UpdateItemRequestDTO requestDTO) {
        UpdateItemResponseDTO responseDTO = itemService.updateItem(itemId, requestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "아이템이 수정되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/sale-items")
    public ResponseEntity<Map<String, Object>> readAllItem() {
        List<ReadItemResponseDTO> responseDTO = itemService.readAllItem();
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "아이템이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }
}
