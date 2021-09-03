package br.com.alura.lojaweb.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestHelper {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static String getRequestBody(HttpServletRequest request) throws IOException {
        return request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
    }

    public static <T> T parseToObject(HttpServletRequest request, Class<T> clazz) throws IOException {
            return MAPPER.readValue(RequestHelper.getRequestBody(request), clazz);
    }

    public static <T> String parseToJson(T payload) throws JsonProcessingException {
        return MAPPER.writeValueAsString(payload);
    }
}
