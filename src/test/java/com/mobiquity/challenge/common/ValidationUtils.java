package com.mobiquity.challenge.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public final class ValidationUtils {

    private static final String EMAIL_VALIDATION_REGEX = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";

    private ValidationUtils(){};

    /**
     * This is method is used for checking email format
     */
    public static Boolean checkEmailFormat(String email) {
        return email.matches(EMAIL_VALIDATION_REGEX);
    }

    public static Boolean ifBodyEmpty(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(json, new TypeReference<List<String>>() {
        }).size() == 0;
    }
}
