package com.microservices.order.mapper;

import com.microservices.order.model.domain.OrderItem;
import com.microservices.order.model.dto.ItemOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component
public class ItemOrderMapper {

    private OrderMapper orderMapper;

    public OrderItem toEntity(ItemOrderDTO dto) {
        OrderItem item = new OrderItem();
        item.setQuantity(dto.getQuantity());
        item.setProductId(dto.getProductId());
        item.setUnitPrice(BigDecimal.valueOf(dto.getUnitPrice()));

        if(Objects.nonNull(dto.getOrderDTO())) {
            item.setOrder(orderMapper.toEntity(dto.getOrderDTO()));
        }

        return item;
    }

    public ItemOrderDTO toDto(OrderItem item) {
        ItemOrderDTO dto = new ItemOrderDTO();
        dto.setId(item.getId().intValue());
        dto.setQuantity(item.getQuantity());
        dto.setProductId(item.getProductId());
        dto.setUnitPrice(item.getUnitPrice().doubleValue());

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

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
}
