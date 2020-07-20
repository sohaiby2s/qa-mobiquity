package com.mobiquity.challenge.common;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

public class ConfigReader {

    public static final String API_URL = getConfigReader().get("url").asText();

    private static JsonNode jsonNode;

    public static JsonNode getConfigReader() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(new File("src/test/resources/configuration/configurations.json"));
            jsonNode = jsonNode.get(getEnv());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }


    public static String getEnv() {
        return StringUtils.isEmpty(System.getProperty("env")) ? "dev1" : System.getProperty("env") ;
    }

}
