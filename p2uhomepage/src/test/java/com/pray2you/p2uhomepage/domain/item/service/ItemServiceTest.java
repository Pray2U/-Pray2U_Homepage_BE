//package com.pray2you.p2uhomepage.domain.item.service;
//
//import com.google.gson.Gson;
//import com.pray2you.p2uhomepage.domain.event.dto.response.ReadEventResponseDTO;
//import com.pray2you.p2uhomepage.domain.event.dto.response.UpdateEventResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.request.CreateItemRequestDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.request.UpdateItemRequestDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.response.CreateItemResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.response.DeleteItemResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.response.ReadItemResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.dto.response.UpdateItemResponseDTO;
//import com.pray2you.p2uhomepage.domain.item.entity.Item;
//import com.pray2you.p2uhomepage.domain.item.repository.ItemRepository;
//import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.CreateMemberApprovalResponseDTO;
//import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.DeleteMemberApprovalResponseDTO;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyBoolean;
//
//@ExtendWith(MockitoExtension.class)
//class ItemServiceTest {
//
//    @Mock
//    private ItemRepository itemRepository;
//
//    @InjectMocks
//    private ItemService itemService;
//
//    @Test
//    @DisplayName("## 아이템 등록 서비스 테스트 ##")
//    void createItem() {
//        StringBuilder itemCreateRequestJson = new StringBuilder();
//        itemCreateRequestJson.append("{")
//                .append("\"itemName\": \"아메리카노\",")
//                .append("\"itemDescription\": \"아메리카노 구매권입니다.\",")
//                .append("\"imgUrl\": \"gildongImgCloud.com/americano.jpg\",")
//                .append("\"point\": 1")
//                .append("}");
//
//        CreateItemRequestDTO createItemRequestDTO = new Gson().fromJson(itemCreateRequestJson.toString(), CreateItemRequestDTO.class);
//
//        Item item = Item.builder()
//                .itemName("아메리카노")
//                .itemDescription("아메리카노 구매권입니다.")
//                .imgUrl("gildongImgCloud.com/americano.jpg")
//                .point(1L)
//                .build();
//
//        CreateItemResponseDTO responseDTO = CreateItemResponseDTO.toDTO(item);
//
//        //given
//        BDDMockito.given(itemRepository.save(any())).willReturn(item);
//
//        //when
//        CreateItemResponseDTO result = itemService.createItem(createItemRequestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("##  아이템 삭제 서비스 테스트 ##")
//    void deleteItem() {
//
//        Item item = Item.builder()
//                .itemName("아메리카노")
//                .itemDescription("아메리카노 구매권입니다.")
//                .imgUrl("gildongImgCloud.com/americano.jpg")
//                .point(1L)
//                .build();
//
//        item.delete();
//
//        DeleteItemResponseDTO responseDTO = DeleteItemResponseDTO.toDTO(item);
//
//        //given
//        BDDMockito.given(itemRepository.findItemByIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(item));
//        item.delete();
//        BDDMockito.given(itemRepository.save(any())).willReturn(item);
//
//        //when
//        DeleteItemResponseDTO result = itemService.deleteItem(item.getId());
//
//        //then
//        //멤버 상태 deleted로 바꾼후 dto로 변경 후 결과 비교
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//
//    }
//
//    @Test
//    @DisplayName("## 아이템 수정 서비스 테스트 ##")
//    void updateItem() {
//        StringBuilder itemUpdateRequestJson = new StringBuilder();
//        itemUpdateRequestJson.append("{")
//                .append("\"itemName\": \"아메리카노\",")
//                .append("\"itemDescription\": \"아메리카노 구매권입니다.\",")
//                .append("\"imgUrl\": \"gildongImgCloud.com/americano.jpg\",")
//                .append("\"point\": 1")
//                .append("}");
//
//        UpdateItemRequestDTO requestDTO = new Gson().fromJson(itemUpdateRequestJson.toString(), UpdateItemRequestDTO.class);
//
//        Item item = Item.builder()
//                .itemName("아메리카노")
//                .itemDescription("아메리카노 구매권입니다.")
//                .imgUrl("gildongImgCloud.com/americano.jpg")
//                .point(1L)
//                .build();
//
//        UpdateItemResponseDTO responseDTO = UpdateItemResponseDTO.toDTO(item);
//
//        //given
//        BDDMockito.given(itemRepository.findItemByIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(item));
//        BDDMockito.given(itemRepository.save(any())).willReturn(item);
//
//        //when
//        UpdateItemResponseDTO result = itemService.updateItem(item.getId(), requestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("##  아이템 조회 서비스 테스트 ##")
//    void readAllItem() {
//        Item item = Item.builder()
//                .itemName("아메리카노")
//                .itemDescription("아메리카노 구매권입니다.")
//                .imgUrl("gildongImgCloud.com/americano.jpg")
//                .point(1L)
//                .build();
//
//        List<Item> items = List.of(item);
//
//        List<ReadItemResponseDTO> responseDTOs = items.stream()
//                .map(ReadItemResponseDTO::toDTO)
//                .collect(Collectors.toList());
//
//        //given
//        BDDMockito.given(itemRepository.findByDeleted(anyBoolean())).willReturn(items);
//
//        //when
//        List<ReadItemResponseDTO> result = itemService.readAllItem();
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTOs);
//    }
//}