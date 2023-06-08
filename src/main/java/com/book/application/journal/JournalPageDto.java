package com.book.application.journal;

import com.book.application.QueryPage;
import com.book.domain.enums.JournalType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinx
 */
@Getter
@Setter
public class JournalPageDto extends QueryPage {

    /**
     * 收入：1
     * 支出：-1
     */
    private JournalType type;
}
