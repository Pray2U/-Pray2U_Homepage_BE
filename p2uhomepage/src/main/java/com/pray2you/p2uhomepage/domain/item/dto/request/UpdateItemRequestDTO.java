package com.pray2you.p2uhomepage.domain.item.dto.request;

import com.pray2you.p2uhomepage.domain.item.entity.Item;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRequestDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateItemRequestDTO {

    @NotBlank
    private String imgUrl;
    @NotBlank
    private String itemName;
    @NotNull
    private long point;
    @NotBlank
    private String itemDescription;

    public Item toEntity(Item item) {
        return item.update(itemName, imgUrl, point, itemDescription);
    }
}
