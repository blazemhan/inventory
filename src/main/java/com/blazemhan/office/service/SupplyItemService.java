package com.blazemhan.office.service;

import com.blazemhan.office.entity.SupplyItem;
import com.blazemhan.office.repository.SupplyItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyItemService {

    private final SupplyItemRepository itemRepository;

    public SupplyItemService(SupplyItemRepository supplyItemRepository) {
        this.itemRepository = supplyItemRepository;
    }

    // Add New Supply Item
    public SupplyItem addSupplyItem(SupplyItem item) {
        if (itemRepository.existsByName(item.getName())) {
            throw new RuntimeException("Item already exists");
        }
        return itemRepository.save(item);
    }

    // Get All Items
    public List<SupplyItem> getAllItems() {
        return itemRepository.findAll();
    }

    // Find Item by ID
    public SupplyItem getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
