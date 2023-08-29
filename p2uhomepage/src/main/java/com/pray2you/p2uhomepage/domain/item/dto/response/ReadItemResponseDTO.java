package com.pray2you.p2uhomepage.domain.item.dto.response;

import com.pray2you.p2uhomepage.domain.event.dto.response.ReadEventResponseDTO;
import com.pray2you.p2uhomepage.domain.item.entity.Item;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadItemResponseDTO {

    private Long itemId;
    private String imgUrl;
    private String itemName;
    private String itemDescription;
    private long point;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ReadItemResponseDTO(Long itemId, String imgUrl, String itemName, String itemDescription, long point, LocalDateTime createdDate, LocalDateTime modifiedDate) {
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
                .createdDate(item.getCreatedDate())
                .modifiedDate(item.getModifiedDate())
                .build();
    }
}
