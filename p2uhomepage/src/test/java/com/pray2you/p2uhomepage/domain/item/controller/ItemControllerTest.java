//package com.pray2you.p2uhomepage.domain.item.controller;
//
//import com.pray2you.p2uhomepage.domain.item.dto.response.CreateItemResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.response.DeleteItemResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.response.ReadItemResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.response.UpdateItemResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.service.ItemService;
//import com.pray2you.p2uhomepage.global.config.WebSecurityConfigure;
//import com.pray2you.p2uhomepage.global.security.jwt.JwtAuthenticationFilter;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.*;
//
//@WebMvcTest(controllers = ItemController.class,
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigure.class, JwtAuthenticationFilter.class})
//        })
//@AutoConfigureMockMvc(addFilters = false)
//class ItemControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ItemService itemService;
//
//    @Test
//    @DisplayName("## 상품 등록 컨트롤러 테스트 ##")
//    void createItem() throws Exception {
//        StringBuilder itemCreateRequestJson = new StringBuilder();
//        itemCreateRequestJson.append("{")
//                .append("\"itemName\": \"아메리카노\",")
//                .append("\"itemDescription\": \"아메리카노 구매권입니다.\",")
//                .append("\"imgUrl\": \"gildongImgCloud.com/americano.jpg\",")
//                .append("\"point\": 1")
//                .append("}");
//
//        CreateItemResponseDTO responseDTO = CreateItemResponseDTO.builder()
//                .itemId(1L)
//                .itemName("이벤트")
//                .imgUrl("gildongImgCloud.com/americano.jpg")
//                .itemDescription("아메리카노 구매권입니다.")
//                .point(1L)
//                .createdDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(itemService.createItem(any())).willReturn(responseDTO);
//
//        String json = itemCreateRequestJson.toString();
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/sale-items")
//                .with(SecurityMockMvcRequestPostProcessors.csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
//
//    @Test
//    @DisplayName("## 아이템 삭제 컨트롤러 테스트 ##")
//    void deleteItem() throws Exception {
//        DeleteItemResponseDTO responseDTO = DeleteItemResponseDTO.builder()
//                .itemId(1L)
//                .itemName("이벤트")
//                .imgUrl("gildongImgCloud.com/americano.jpg")
//                .itemDescription("아메리카노 구매권입니다.")
//                .point(1L)
//                .createdDate(LocalDateTime.now())
//                .deletedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(itemService.deleteItem(any())).willReturn(responseDTO);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/sale-items/1")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @DisplayName("## 아이템 수정 컨트롤러 테스트 ##")
//    void updateItem() throws Exception {
//        StringBuilder itemUpdateRequestJson = new StringBuilder();
//        itemUpdateRequestJson.append("{")
//                .append("\"itemName\": \"아메리카노\",")
//                .append("\"itemDescription\": \"아메리카노 구매권입니다.\",")
//                .append("\"imgUrl\": \"gildongImgCloud.com/americano.jpg\",")
//                .append("\"point\": 1")
//                .append("}");
//
//        UpdateItemResponseDTO responseDTO = UpdateItemResponseDTO.builder()
//                .itemId(1L)
//                .itemName("이벤트")
//                .imgUrl("gildongImgCloud.com/americano.jpg")
//                .itemDescription("아메리카노 구매권입니다.")
//                .point(1L)
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(itemService.updateItem(anyLong(), any())).willReturn(responseDTO);
//
//        String json = itemUpdateRequestJson.toString();
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/sale-items/1")
//                .with(SecurityMockMvcRequestPostProcessors.csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
//
//    @Test
//    @DisplayName("## 아이템 조회 컨트롤러 테스트 ##")
//    void readAllItem() throws Exception {
//        ReadItemResponseDTO responseDTO = ReadItemResponseDTO.builder()
//                .itemId(1L)
//                .itemName("이벤트")
//                .imgUrl("gildongImgCloud.com/americano.jpg")
//                .itemDescription("아메리카노 구매권입니다.")
//                .point(1L)
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        List<ReadItemResponseDTO> responseDTOs = List.of(responseDTO);
//
//        //given
//        BDDMockito.given(itemService.readAllItem()).willReturn(responseDTOs);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/sale-items")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}