package com.book.domain;

/**
 * 合并标记接口 实现该接口可获得判空方法
 *
 * @author jinx
 */
public interface MergeAware {

    /**
     * 是否可以merge该字段
     *
     * @param val 字段值
     * @return ture：可以合并
     */
    default boolean couldMerge(String val) {
        return val != null && !val.isEmpty();
    }

}
