package com.pray2you.p2uhomepage.domain.order.controller;

import com.pray2you.p2uhomepage.domain.order.dto.response.AcceptOrderResponseDTO;
import com.pray2you.p2uhomepage.domain.order.dto.response.CreateOrderResponseDTO;
import com.pray2you.p2uhomepage.domain.order.dto.response.ReadOrderResponseDTO;
import com.pray2you.p2uhomepage.domain.order.service.OrderService;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/items/{itemId}/orders")
    public ResponseEntity<Map<String, Object>> createOrder(Authentication authentication, @PathVariable(value = "itemId") long id) {

        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        CreateOrderResponseDTO responseDTO = orderService.createOrder(userId, id);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "주문이 생성되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/admin/orders")
    public ResponseEntity<Map<String, Object>> readAllOrders() {
        List<ReadOrderResponseDTO> responseDTO = orderService.readAllOrder();

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "전체 주문이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/orders/me")
    public ResponseEntity<Map<String, Object>> readMyOrders(Authentication authentication) {

        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        List<ReadOrderResponseDTO> responseDTO = orderService.readMyOrder(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "나의 주문이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("api/admin/orders/{orderId}/accept")
    public ResponseEntity<Map<String, Object>> acceptOrder(@PathVariable(value = "orderId") long orderId) {
        AcceptOrderResponseDTO responseDTO = orderService.acceptOrder(orderId);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "주문이 승인되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }
}
