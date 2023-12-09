package com.pray2you.p2uhomepage.domain.order.dto.response;

import com.pray2you.p2uhomepage.domain.item.dto.response.ReadItemResponseDTO;
import com.pray2you.p2uhomepage.domain.item.entity.Item;
import com.pray2you.p2uhomepage.domain.order.entity.Order;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ReadOrderResponseDTO {
    private final long orderId;
    private final SimpleUserInfoResponseDTO user;
    private final ReadItemResponseDTO item;
    private final boolean useStatus;
    private final LocalDateTime useDate;
    private final LocalDateTime createDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private ReadOrderResponseDTO(
            @NonNull Long orderId,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull ReadItemResponseDTO item,
            @NonNull Boolean useStatus,
            LocalDateTime useDate,
            @NonNull LocalDateTime createDate,
            @NonNull LocalDateTime modifiedDate) {
        this.orderId = orderId;
        this.user = user;
        this.item = item;
        this.useStatus = useStatus;
        this.useDate = useDate;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadOrderResponseDTO toDTO(Order order) {
        return builder()
                .orderId(order.getId())
                .user(SimpleUserInfoResponseDTO.toDTO(order.getUser()))
                .item(ReadItemResponseDTO.toDTO(order.getItem()))
                .useDate(order.getUseDate())
                .useStatus(order.isUseStatus())
                .createDate(order.getCreatedDate())
                .modifiedDate(order.getModifiedDate())
                .build();
    }
}
