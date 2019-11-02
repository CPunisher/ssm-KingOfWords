package com.cpunisher.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

    public static String stringify(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T parse(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;

        try {
            t = objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }
}
