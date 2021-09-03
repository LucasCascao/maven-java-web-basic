package br.com.alura.lojaweb.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestHelper {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static String getRequestBody(HttpServletRequest request) throws IOException {
        return request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
    }

    public static <T> T parseToObject(String payload, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(payload, clazz);
    }

    public static <T> String parseToJson(T payload) throws JsonProcessingException {
        return mapper.writeValueAsString(payload);
    }
}
