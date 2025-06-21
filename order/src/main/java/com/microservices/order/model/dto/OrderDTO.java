package com.microservices.order.model.dto;

import com.microservices.order.model.domain.OrderItem;
import com.microservices.order.type.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Integer customerId;
    private Integer orderId;
    private LocalDateTime orderDate;
    private List<ItemOrderDTO> orderItem;
    private Integer quantity;
    private PaymentMethod paymentMethod;
    private Double total;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<ItemOrderDTO> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<ItemOrderDTO> orderItem) {
        this.orderItem = orderItem;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
