package com.pray2you.p2uhomepage.domain.item.repository;
import com.pray2you.p2uhomepage.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findItemByIdAndDeleted(Long id, boolean deleted);
    List<Item> findByDeleted(boolean deleted);
}
