package com.book.vender.wx;

/**
 * @author jinx
 */
public interface WxResponse {

    boolean success();

    String errorTips();

    /**
     * 响应核心参数
     * 约定：多个,隔开
     */
    String kernel();
}
