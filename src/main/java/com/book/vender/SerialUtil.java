package com.book.vender;

import com.book.infra.config.JacksonConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

/**
 * @author jinx
 */
public class SerialUtil {

    private final static ObjectMapper OM = JacksonConfig.objectMapper();

    @SneakyThrows
    public static String serial(Object source) {
        return OM.writeValueAsString(source);
    }

    @SneakyThrows
    public static <T> T deSerial(String source, Class<T> clazz) {
        if (StringUtils.hasText(source)) {
            return OM.readValue(source, clazz);
        }

        return clazz.getConstructor().newInstance();
    }

}
