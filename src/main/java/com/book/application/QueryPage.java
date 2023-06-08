package com.book.application;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

/**
 * @author jinx
 */
@Getter
@Setter
public class QueryPage {

    /**
     * 当前页码(默认1)
     */
    private int current = 1;
    /**
     * 分页大小(默认10)
     */
    private int size = 10;


    public Pageable page() {
        return PageRequest.of(getCurrent(), size);
    }

    public int getCurrent() {
        Assert.isTrue(current > 0, "页数应该大于0");
        return current - 1;
    }
}
