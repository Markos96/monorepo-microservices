package com.microservices.order.client;

import com.microservices.order.model.dto.GraphQLRequestDTO;
import com.microservices.order.model.dto.GraphQLResponseDTO;
import com.microservices.order.model.dto.ProductDTO;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Component
public class WebClientProduct {

    private final WebClient webClient;

    public WebClientProduct(@LoadBalanced WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://api-product-service/graphql")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<ProductDTO> getProducts() {
        String query = "{ getProducts { id name description price } }";

        GraphQLRequestDTO request = new GraphQLRequestDTO(query);

        GraphQLResponseDTO response = webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(GraphQLResponseDTO.class)
                .block();

        return response.extractList("getProducts", ProductDTO.class);
    }

}
