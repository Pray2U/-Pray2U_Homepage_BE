package com.pray2you.p2uhomepage.domain.item.dto.response;

import com.pray2you.p2uhomepage.domain.event.dto.response.ReadEventResponseDTO;
import com.pray2you.p2uhomepage.domain.item.entity.Item;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ReadItemResponseDTO {

    private final long itemId;
    private final String imgUrl;
    private final String itemName;
    private final String itemDescription;
    private final int point;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private ReadItemResponseDTO(
            @NonNull Long itemId,
            @NonNull String imgUrl,
            @NonNull String itemName,
            @NonNull String itemDescription,
            @NonNull Integer point,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime modifiedDate) {
        this.itemId = itemId;
        this.imgUrl = imgUrl;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.point = point;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadItemResponseDTO toDTO(Item item) {
        return builder()
                .itemId(item.getId())
                .itemName(item.getItemName())
                .imgUrl(item.getImgUrl())
                .itemDescription(item.getItemDescription())
                .point(item.getPoint())
                .createdDate(item.getCreatedDate())
                .modifiedDate(item.getModifiedDate())
                .build();
    }
}
