package com.book.application.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author jinx
 */
@Getter
@Setter
public class UserDto {
    /**
     * 手机号
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
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
