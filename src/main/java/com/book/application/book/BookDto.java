package com.book.application.book;

import com.book.application.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jinx
 */
@Getter
@Setter
public class BookDto {
    /**
     * 账本id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * logo图
     */
    private String logo;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 成员
     */
    private List<UserDto> members;
    /**
     * 是否是默认账本
     */
    private boolean defaultSelector;
}
