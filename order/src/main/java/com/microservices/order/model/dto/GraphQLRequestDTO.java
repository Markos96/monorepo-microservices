package com.microservices.order.model.dto;

public class GraphQLRequestDTO {
    private String query;

    public GraphQLRequestDTO(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
