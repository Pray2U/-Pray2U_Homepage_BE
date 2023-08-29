package com.pray2you.p2uhomepage.domain.item.dto.response;

import com.pray2you.p2uhomepage.domain.item.entity.Item;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeleteItemResponseDTO {

    private Long itemId;
    private String imgUrl;
    private String itemName;
    private String itemDescription;
    private Long point;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;

    @Builder
    public DeleteItemResponseDTO(Long itemId, String imgUrl, String itemName, String itemDescription, Long point, LocalDateTime createdDate, LocalDateTime deletedDate) {
        this.itemId = itemId;
        this.imgUrl = imgUrl;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.point = point;
        this.createdDate = createdDate;
        this.deletedDate = deletedDate;
    }

    public static DeleteItemResponseDTO toDTO(Item item) {
        return builder()
                .itemId(item.getId())
                .imgUrl(item.getImgUrl())
                .itemName(item.getItemName())
                .itemDescription(item.getItemDescription())
                .createdDate(item.getCreatedDate())
                .deletedDate(item.getModifiedDate())
                .build();
    }
}
