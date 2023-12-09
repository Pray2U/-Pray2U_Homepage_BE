package com.pray2you.p2uhomepage.domain.order.repository;

import com.pray2you.p2uhomepage.domain.order.entity.Order;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);
}
