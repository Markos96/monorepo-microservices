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

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T extract(String key, Class<T> valueType) {
        return objectMapper.convertValue(data.get(key), valueType);
    }

    public <T> List<T> extractList(String key, Class<T> valueType) {
        return objectMapper.convertValue(
                data.get(key),
                objectMapper.getTypeFactory().constructCollectionType(List.class, valueType)
        );
    }
}
