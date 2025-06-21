package com.microservices.order.service;

import com.microservices.order.client.FeignClientRequest;
import com.microservices.order.client.WebClientProduct;
import com.microservices.order.mapper.ItemOrderMapper;
import com.microservices.order.mapper.OrderMapper;
import com.microservices.order.model.domain.Order;
import com.microservices.order.model.domain.OrderItem;
import com.microservices.order.model.dto.ClientDTO;
import com.microservices.order.model.dto.ItemOrderDTO;
import com.microservices.order.model.dto.OrderDTO;
import com.microservices.order.model.dto.ProductDTO;
import com.microservices.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private FeignClientRequest feignClientRequest;

    private OrderMapper orderMapper;

    private WebClientProduct webClientProduct;

    private ItemOrderMapper itemOrderMapper;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {

        ClientDTO clientDTOS = feignClientRequest.getClientById(orderDTO.getCustomerId());

        if(Objects.isNull(clientDTOS)) {
            throw new RuntimeException(String.format("Client with code %d not found",  orderDTO.getCustomerId()));
        }

        List<ProductDTO> productList = webClientProduct.getProducts();

        double total = orderDTO.getOrderItem().stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();

        orderDTO.setOrderDate(LocalDateTime.now());
        orderDTO.setTotal(total);
        orderDTO.setQuantity(orderDTO.getOrderItem().size());

        Order orderSaved = this.orderMapper.toEntity(orderDTO);

        Order finalOrderSaved = orderSaved;
        Order finalOrderSaved1 = orderSaved;
        List<OrderItem> itemOrderList = productList.stream()
                .map(productDTO -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(productDTO.getId());
                    item.setQuantity(1); // Desarrolladr logicar para cantidad
                    item.setUnitPrice(BigDecimal.valueOf(productDTO.getPrice()));
                    item.setOrder(finalOrderSaved1);
                    return item;
                })
                .toList();

        orderSaved.setOrderItem(itemOrderList);

        orderSaved = this.orderRepository.save(orderSaved);

        return this.orderMapper.toDTO(orderSaved);
    }

    public OrderDTO getOrderById(Long id) {
        Order order = this.orderRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException(String.format("Order with id %d not found", id)));

        OrderDTO orderDTO = this.orderMapper.toDTO(order);
        orderDTO.setOrderItem(this.itemOrderMapper.toDTOS(order.getOrderItem()));

        return orderDTO;
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

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) { this.orderMapper = orderMapper;}
}
