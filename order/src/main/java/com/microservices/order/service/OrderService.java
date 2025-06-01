package com.microservices.order.service;

import com.microservices.order.client.WebClientProduct;
import com.microservices.order.model.domain.Order;
import com.microservices.order.model.dto.ProductDTO;
import com.microservices.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private WebClientProduct webClientProduct;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void saveOrder(@RequestBody Order order) {

        List<ProductDTO> productDTOS = webClientProduct.getProducts();

        System.out.println("Order saved");
    }

    @Autowired
    public void setWebClientProduct(WebClientProduct webClientProduct) {
        this.webClientProduct = webClientProduct;
    }
}
