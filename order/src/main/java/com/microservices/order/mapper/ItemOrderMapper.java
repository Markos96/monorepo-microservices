package com.microservices.order.mapper;

import com.microservices.order.model.domain.OrderItem;
import com.microservices.order.model.dto.ItemOrderDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemOrderMapper {

    public OrderItem toEntity(ItemOrderDTO dto) {
        OrderItem item = new OrderItem();
        item.setQuantity(dto.getQuantity());
        item.setProductId(dto.getProductId());
        item.setUnitPrice(BigDecimal.valueOf(dto.getUnitPrice()));
        item.setOrder(dto.getOrder());

        return item;
    }

    public ItemOrderDTO toDto(OrderItem item) {
        ItemOrderDTO dto = new ItemOrderDTO();
        dto.setQuantity(item.getQuantity());
        dto.setProductId(item.getProductId());
        dto.setUnitPrice(item.getUnitPrice().doubleValue());
        dto.setOrder(item.getOrder());

        return dto;
    }

    public List<ItemOrderDTO> toDTOS(List<OrderItem> items) {
        return items.stream()
                .map(this::toDto)
                .toList();
    }

    public List<OrderItem> toEntities(List<ItemOrderDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
