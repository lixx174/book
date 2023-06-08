package com.book.application.journal;

import com.book.domain.enums.JournalCategory;
import com.book.domain.enums.JournalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author jinx
 */
@Getter
@Setter
public class JournalDto {

    /**
     * 流水id
     */
    private Long id;
    /**
     * 收入：1
     * 支出：-1
     */
    private JournalType type;
    /**
     * 分类太多 询问服务端同学
     */
    private JournalCategory category;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 备足
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
