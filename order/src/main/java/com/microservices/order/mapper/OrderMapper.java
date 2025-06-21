package com.microservices.order.mapper;

import com.microservices.order.model.domain.Order;
import com.microservices.order.model.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ItemOrderMapper itemOrderMapper;

    public OrderMapper(@Lazy ItemOrderMapper itemOrderMapper) {
        this.itemOrderMapper = itemOrderMapper;
    }

    public Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderDate(orderDTO.getOrderDate());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setTotal(orderDTO.getTotal());
        order.setQuantity(orderDTO.getQuantity());
        order.setOrderItem(itemOrderMapper.toEntities(orderDTO.getOrderItem()));
        return order;
    }

    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setOrderItem(itemOrderMapper.toDTOS(order.getOrderItem()));

        return orderDTO;
    }
}
