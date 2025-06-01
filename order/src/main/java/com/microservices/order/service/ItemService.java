package com.microservices.order.service;

import com.microservices.order.mapper.ItemOrderMapper;
import com.microservices.order.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemOrderMapper itemOrderMapper, ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
