package com.pray2you.p2uhomepage.domain.item.dto.response;

import com.pray2you.p2uhomepage.domain.item.entity.Item;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateItemResponseDTO {

    private Long itemId;
    private String imgUrl;
    private String itemName;
    private String itemDescription;
    private Long point;
    private LocalDateTime createdDate;

    @Builder
    public CreateItemResponseDTO(Long itemId, String imgUrl, String itemName, String itemDescription, Long point, LocalDateTime createdDate) {
        this.itemId = itemId;
        this.imgUrl = imgUrl;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.point = point;
        this.createdDate = createdDate;
    }

    public static CreateItemResponseDTO toDTO(Item item) {
        return builder()
                .itemId(item.getId())
                .imgUrl(item.getImgUrl())
                .itemName(item.getItemName())
                .itemDescription(item.getItemDescription())
                .point(item.getPoint())
                .createdDate(item.getCreatedDate())
                .build();
    }
}
