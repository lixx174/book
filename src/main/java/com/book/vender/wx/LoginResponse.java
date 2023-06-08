package com.book.vender.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * @author jinx
 */
@Getter
@Setter
public class LoginResponse implements WxResponse {
    @JsonProperty("session_key")
    private String sessionKey;
    @JsonProperty("unionid")
    private String unionId;
    @JsonProperty("openid")
    private String openId;
    @JsonProperty("errcode")
    private String errCode;
    @JsonProperty("errmsg")
    private String errMsg;

    public boolean success() {
        return !StringUtils.hasText(errCode) || "0".equals(errCode);
    }

    @Override
    public String errorTips() {
        return "[code:" + errCode + "] " + "[msg:" + errMsg + "]";
    }

    @Override
    public String kernel() {
        return openId + "," + unionId;
    }

}
