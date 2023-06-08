package com.book.application;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

/**
 * @author jinx
 */
@Getter
@Setter
public class ReplyPage<T> {

    /**
     * 当前页码
     */
    private int current;
    /**
     * 分页大小
     */
    private int size;
    /**
     * 总页数
     */
    private int total;
    /**
     * 数据
     */
    private List<T> records;
    private ReplyPage(int current, int size, int total, List<T> records) {
        this.current = current + 1;
        this.size = size;
        this.total = total;
        this.records = records;
    }

    public static <T> ReplyPage<T> of(Page<T> page) {
        return of(page.getNumber(), page.getSize(), page.getTotalPages(), page.getContent());
    }

    public static <T> ReplyPage<T> of(Page<?> page, List<T> records) {
        return of(page.getNumber(), page.getSize(), page.getTotalPages(), records);
    }

    /**
     * 手动分页
     *
     * @param page    分页参数
     * @param records 记录列表
     */
    public static <T> ReplyPage<T> of(QueryPage page, List<T> records) {
        int total = (int) Math.ceil((double) records.size() / (double) page.getSize());
        int fromIndex = page.getCurrent() * page.getSize();
        int tolIndex = fromIndex + page.getSize();

        if (fromIndex >= records.size()) {
            return of(page.getCurrent(), page.getSize(), total, Collections.emptyList());
        }
        if (tolIndex > records.size()) {
            tolIndex = records.size();
        }

        return of(page.getCurrent(), page.getSize(), total, records.subList(fromIndex, tolIndex));
    }

    public static <T> ReplyPage<T> of(int current, int size, int total, List<T> records) {
        return new ReplyPage<>(current, size, total, records);
    }
}
