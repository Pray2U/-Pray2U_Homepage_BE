package com.pray2you.p2uhomepage.domain.item.service;

import com.pray2you.p2uhomepage.domain.item.dto.request.CreateItemRequestDTO;
import com.pray2you.p2uhomepage.domain.item.dto.request.UpdateItemRequestDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.CreateItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.DeleteItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.ReadItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.UpdateItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.entity.Item;
import com.pray2you.p2uhomepage.domain.item.repository.ItemRepository;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public CreateItemResponseDTO createItem(CreateItemRequestDTO requestDTO) {
        Item savedItem = itemRepository.save(requestDTO.toEntity());
        return CreateItemResponseDTO.toDTO(savedItem);
    }

    public DeleteItemResponseDTO deleteItem(long itemId) {
        Item deleteItem = itemRepository.findItemByIdAndDeleted(itemId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_ITEM_EXCEPTION));
        deleteItem.delete();
        Item deletedItem = itemRepository.save(deleteItem);
        return DeleteItemResponseDTO.toDTO(deletedItem);
    }

    public UpdateItemResponseDTO updateItem(long itemId, UpdateItemRequestDTO requestDTO) {
        Item item = itemRepository.findItemByIdAndDeleted(itemId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_ITEM_EXCEPTION));
        Item updateItem = requestDTO.toEntity(item);
        Item updatedItem = itemRepository.save(updateItem);
        return UpdateItemResponseDTO.toDTO(updatedItem);
    }

    public List<ReadItemResponseDTO> readAllItem() {
        List<Item> allItem = itemRepository.findByDeleted(false);
        return allItem.stream()
                .map(ReadItemResponseDTO::toDTO)
                .collect(Collectors.toList());
    }
}
