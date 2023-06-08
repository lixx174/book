package com.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jinx
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "book")
public class BookProperties {

    private String apiPrefix;

    /**
     * 微信密钥
     */
    private Applet wxApplet = new Applet();


    @Getter
    @Setter
    public static class Applet {
        private String appId;
        private String appSecret;
    }
}
