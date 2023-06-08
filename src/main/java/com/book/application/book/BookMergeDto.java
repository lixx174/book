package com.book.application.book;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 账本修改表单
 *
 * @author jinx
 */
@Getter
@Setter
public class BookMergeDto {
    /**
     * 账本id
     *
     * @required
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
     * 成员id数组
     */
    private List<Long> memberIds;
    /**
     * 默认账本标记
     */
    private boolean defaultSelector;
}
