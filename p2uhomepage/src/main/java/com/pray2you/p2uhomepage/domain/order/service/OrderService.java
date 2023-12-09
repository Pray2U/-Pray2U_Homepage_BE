package com.pray2you.p2uhomepage.domain.order.service;

import com.pray2you.p2uhomepage.domain.item.entity.Item;
import com.pray2you.p2uhomepage.domain.item.repository.ItemRepository;
import com.pray2you.p2uhomepage.domain.order.dto.response.AcceptOrderResponseDTO;
import com.pray2you.p2uhomepage.domain.order.dto.response.CreateOrderResponseDTO;
import com.pray2you.p2uhomepage.domain.order.dto.response.ReadOrderResponseDTO;
import com.pray2you.p2uhomepage.domain.order.entity.Order;
import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;
import com.pray2you.p2uhomepage.domain.point.event.OrderItemEvent;
import com.pray2you.p2uhomepage.domain.order.repository.OrderRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public CreateOrderResponseDTO createOrder(long userId, long itemId) {
        User user = findUser(userId);

        Item item = findItem(itemId);

        Order order = Order.builder()
                .user(user)
                .item(item)
                .useStatus(false)
                .build();

        Order savedOrder = orderRepository.save(order);

        //아이템 금액만큼 포인트 감소
        applicationEventPublisher.publishEvent(new OrderItemEvent(PointContent.ORDER, -(savedOrder.getItem().getPoint()), user));

        return CreateOrderResponseDTO.toDTO(savedOrder);
    }

    public List<ReadOrderResponseDTO> readMyOrder(long userId) {

        User user = findUser(userId);

        List<Order> orders = orderRepository.findAllByUser(user);

        return orders.stream().map(ReadOrderResponseDTO::toDTO).collect(Collectors.toList());
    }

    public List<ReadOrderResponseDTO> readAllOrder() {
        List<Order> orderList = orderRepository.findAll();

        return orderList.stream().map(ReadOrderResponseDTO::toDTO).collect(Collectors.toList());
    }

    public AcceptOrderResponseDTO acceptOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_ORDER_EXCEPTION));

        order.acceptOrder();

        Order updatedOrder = orderRepository.save(order);

        return AcceptOrderResponseDTO.toDTO(updatedOrder);
    }

    private User findUser(long userId) {
        return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }

    private Item findItem(long itemId) {
        return itemRepository.findItemByIdAndDeleted(itemId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_ITEM_EXCEPTION));
    }

}
