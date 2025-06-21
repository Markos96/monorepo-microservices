package com.microservices.order.model.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class GraphQLResponseDTO {
    private JsonNode data;

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public <T> List<T> extractList(String key, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(
                data.get(key),
                objectMapper.getTypeFactory().constructCollectionType(List.class, valueType)
        );
    }
}
