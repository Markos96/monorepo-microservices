package com.microservices.order.model.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private Integer orderId;
    private List<>
}
