package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * The CommonContext is an abstract class holds the common functionality
 * that is been used by other context classes
 */

public abstract class CommonContext {

    /**
     * This is generic method of parsing JSON string into JAVA object
     */
    protected final <T> T mapFromJson(String json, Class<T> className) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, className);
    }

    /**
     * This is generic method of parsing JSON string into List of JAVA object
     */
    protected final <T> List<T> mapFromJsonList(String json, Class<T> className) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, className));
    }


}
