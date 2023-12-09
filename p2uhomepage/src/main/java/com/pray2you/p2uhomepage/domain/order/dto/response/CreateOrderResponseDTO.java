package com.pray2you.p2uhomepage.domain.order.dto.response;

import com.pray2you.p2uhomepage.domain.item.dto.response.CreateItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.dto.response.ReadItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.entity.Item;
import com.pray2you.p2uhomepage.domain.order.entity.Order;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class CreateOrderResponseDTO {

    private final long orderId;
    private final long userId;
    private final ReadItemResponseDTO item;
    private final boolean useStatus;
    private final LocalDateTime createDate;
    private final LocalDateTime modifiedDate;

    @Builder
    public CreateOrderResponseDTO(
            @NonNull Long orderId,
            @NonNull Long userId,
            @NonNull ReadItemResponseDTO item,
            @NonNull Boolean useStatus,
            @NonNull LocalDateTime createDate,
            @NonNull LocalDateTime modifiedDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.item = item;
        this.useStatus = useStatus;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }



    public static CreateOrderResponseDTO toDTO(Order order) {
        return builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .item(ReadItemResponseDTO.toDTO(order.getItem()))
                .useStatus(order.isUseStatus())
                .createDate(order.getCreatedDate())
                .modifiedDate(order.getModifiedDate())
                .build();
    }
}
