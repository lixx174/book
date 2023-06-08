package com.book.application.journal;

import com.book.domain.enums.JournalCategory;
import com.book.domain.enums.JournalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author jinx
 */
@Getter
@Setter
public class JournalPersistDto {
    /**
     * 账本id
     *
     * @required
     */
    private Long bookId;
    /**
     * 收入：1
     * 支出：-1
     *
     * @required
     */
    private JournalType type;
    /**
     * 分类太多 询问服务端同学
     *
     * @required
     */
    private JournalCategory category;
    /**
     * 金额
     *
     * @required
     */
    private BigDecimal amount;
    /**
     * 备注
     */
    private String remark;
}
