package com.book.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流水分类
 *
 * @author jinx
 */
@Getter
@AllArgsConstructor
public enum JournalCategory {


    RESTAURANT("1"),
    FOOD("2"),
    TRAFFIC("3");

    @JsonValue
    private final String code;
}
