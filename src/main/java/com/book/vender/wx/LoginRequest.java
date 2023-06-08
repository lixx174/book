package com.book.vender.wx;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author jinx
 */
@Getter
@Setter
public class LoginRequest implements WxRequest {

    private String appId;
    private String appSecret;
    private String code;
    private String grantType = "authorization_code";
    public LoginRequest(String appId, String appSecret, String code) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.code = code;
    }

    public URI constructUri() {
        final String baseUri = "https://api.weixin.qq.com/sns/jscode2session";

        return UriComponentsBuilder.fromHttpUrl(baseUri)
                .queryParam("appid", appId)
                .queryParam("secret", appSecret)
                .queryParam("js_code", code)
                .queryParam("grant_type", grantType)
                .build()
                .toUri();
    }

    @Override
    public Class<? extends WxResponse> expect() {
        return LoginResponse.class;
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.GET;
    }
}
