package com.pray2you.p2uhomepage.domain.item.dto.response;

import com.pray2you.p2uhomepage.domain.item.entity.Item;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class CreateItemResponseDTO {

    private final long itemId;
    private final String imgUrl;
    private final String itemName;
    private final String itemDescription;
    private final long point;
    private final LocalDateTime createdDate;

    @Builder
    private CreateItemResponseDTO(
            @NonNull Long itemId,
            @NonNull String imgUrl,
            @NonNull String itemName,
            @NonNull String itemDescription,
            @NonNull Integer point,
            @NonNull LocalDateTime createdDate) {
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
