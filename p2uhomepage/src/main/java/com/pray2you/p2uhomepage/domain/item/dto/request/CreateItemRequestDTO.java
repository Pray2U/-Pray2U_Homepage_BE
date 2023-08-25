package com.pray2you.p2uhomepage.domain.item.dto.request;

import com.pray2you.p2uhomepage.domain.item.entity.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemRequestDTO {

    @NotBlank
    private String imgUrl;
    @NotBlank
    private String itemName;
    @NotNull
    private long point;
    @NotBlank
    private String itemDescription;

    public Item toEntity() {
        return Item.builder()
                .imgUrl(this.imgUrl)
                .itemName(this.itemName)
                .itemDescription(this.itemDescription)
                .point(this.point)
                .build();
    }
}
