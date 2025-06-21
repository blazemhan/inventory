package com.blazemhan.office.repository;

import com.blazemhan.office.entity.SupplyItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyItemRepository extends JpaRepository<SupplyItem, Long> {

    boolean existsByName(String name);
}