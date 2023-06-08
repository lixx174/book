package com.book.application.book;

import lombok.Getter;
import lombok.Setter;

/**
 * 账本新增表单
 *
 * @author jinx
 */
@Getter
@Setter
public class BookPersistDto {
    /**
     * 名称
     *
     * @required
     */
    private String name;
    /**
     * logo图
     *
     * @required
     */
    private String logo;
}
