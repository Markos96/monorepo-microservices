package com.microservices.order.service;

import com.microservices.order.client.FeignClientRequest;
import com.microservices.order.client.WebClientProduct;
import com.microservices.order.mapper.ItemOrderMapper;
import com.microservices.order.model.domain.Order;
import com.microservices.order.model.dto.ClientDTO;
import com.microservices.order.model.dto.ItemOrderDTO;
import com.microservices.order.model.dto.ProductDTO;
import com.microservices.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private FeignClientRequest feignClientRequest;

    private WebClientProduct webClientProduct;

    private ItemOrderMapper itemOrderMapper;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void saveOrder(@RequestBody Order order) {

        ClientDTO clientDTOS = feignClientRequest.getClientById(order.getCustomerId());

        if(Objects.isNull(clientDTOS)) {
            throw new RuntimeException(String.format("Client with code %d not found",  order.getCustomerId()));
        }

        List<ProductDTO> productList = webClientProduct.getProducts();

        List<ItemOrderDTO> itemOrderList = productList.stream()
                .map(productDTO -> {
                    ItemOrderDTO itemDTO = new ItemOrderDTO();
                    itemDTO.setProductId(productDTO.getId());
                    itemDTO.setQuantity(order.getQuantity());
                    itemDTO.setUnitPrice(productDTO.getPrice());
                    itemDTO.setOrder(order);
                    return itemDTO;
                })
                .toList();

        order.setOrderItem(itemOrderMapper.toEntities(itemOrderList));
        order.setOrderDate(LocalDateTime.now());

        this.orderRepository.save(order);
    }

    @Autowired
    public void setWebClientProduct(WebClientProduct webClientProduct) {
        this.webClientProduct = webClientProduct;
    }

    @Autowired
    public void setFeignClientRequest(FeignClientRequest feignClientRequest) {
        this.feignClientRequest = feignClientRequest;
    }

    @Autowired
    public void setItemOrderMapper(ItemOrderMapper itemOrderMapper) {
        this.itemOrderMapper = itemOrderMapper;
    }
}
