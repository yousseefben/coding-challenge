package com.coding.challenge.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class IntegrationTestUtil {

    public static String convertObjectToJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}