package com.assessment.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.assessment.entity.Item;

public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findByProductId(Long productId);
}