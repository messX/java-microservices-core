package com.example.core.util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageUtils<T> {

    public Map<String, Object> convertToResponse(final Page<T> pageData){
        Map<String, Object> convertedData = new HashMap<>();
        convertedData.put("data", pageData.getContent());
        convertedData.put("current_page", pageData.getNumber());
        convertedData.put("total_items", pageData.getTotalElements());
        convertedData.put("total_pages", pageData.getTotalPages());
        return convertedData;
    }
}
