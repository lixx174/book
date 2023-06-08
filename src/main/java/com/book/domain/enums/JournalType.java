package com.book.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流水类型
 *
 * @author jinx
 */
@Getter
@AllArgsConstructor
public enum JournalType {

    /**
     * 收入
     */
    INCOME("1"),
    /**
     * 支出
     */
    EXPENSES("-1");

    @JsonValue
    private final String code;
}
