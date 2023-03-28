package org.j2os.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
    Bahador, Amirsam
 */
public class JSON {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JSON() {
    }

    public static String convert(Object instance) {
        try {
            return OBJECT_MAPPER.writeValueAsString(instance);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(Class<T> type, Object json) {
        try {
            return OBJECT_MAPPER.readValue(json.toString(), type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
