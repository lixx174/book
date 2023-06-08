package com.book.domain;

import com.book.domain.enums.JournalCategory;
import com.book.domain.enums.JournalType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author jinx
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private JournalType type;
    @Enumerated(EnumType.STRING)
    private JournalCategory category;
    private BigDecimal amount;
    private String remark;
    private LocalDateTime createTime = LocalDateTime.now();
    public Journal(Book book,
                   User user,
                   JournalType type,
                   JournalCategory category,
                   BigDecimal amount,
                   String remark) {
        this.book = book;
        this.user = user;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.remark = remark;
    }
}
