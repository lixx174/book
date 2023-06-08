package com.book.application.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jinx
 */
@Getter
@Setter
public class UserMergeDto {
    /**
     * 手机号(暂定可修改)
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
}
