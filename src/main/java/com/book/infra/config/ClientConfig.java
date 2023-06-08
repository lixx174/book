package com.book.infra.config;

import com.book.infra.proxy.WxClientProxy;
import com.book.vender.wx.WxClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Proxy;

/**
 * @author jinx
 */
@Configuration
@Import(RestTemplate.class)
public class ClientConfig {

    @Bean
    public WxClient wxClient(RestTemplate template) {
        return (WxClient) Proxy.newProxyInstance(
                WxClient.class.getClassLoader(),
                new Class[]{WxClient.class},
                new WxClientProxy(template)
        );
    }
}
