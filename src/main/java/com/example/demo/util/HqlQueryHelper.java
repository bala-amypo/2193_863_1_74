package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class HqlQueryHelper {
    
    public String buildSelectQuery(String entityName, String whereClause) {
        StringBuilder query = new StringBuilder("SELECT e FROM ");
        query.append(entityName).append(" e");
        
        if (whereClause != null && !whereClause.trim().isEmpty()) {
            query.append(" WHERE ").append(whereClause);
        }
        
        return query.toString();
    }
    
    public String buildCountQuery(String entityName, String whereClause) {
        StringBuilder query = new StringBuilder("SELECT COUNT(e) FROM ");
        query.append(entityName).append(" e");
        
        if (whereClause != null && !whereClause.trim().isEmpty()) {
            query.append(" WHERE ").append(whereClause);
        }
        
        return query.toString();
    }
}