package com.microservices.order.model.dto;

import java.util.Map;

public class GraphQLRequestDTO {
    private String query;
    private Map<String, Object> variables;

    public GraphQLRequestDTO(String query, Map<String, Object> variables) {
        this.query = query;
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
