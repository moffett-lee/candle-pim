package com.fast.coding.extension.pagination;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 分页模型
 *
 * @author Bamboo
 * @since 2020-03-18
 */
@Data
public class Pagination<T> {

    /**
     * 总记录数
     */
    private Long rowTotal;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 起始下标
     */
    private int beginIndex;

    /**
     * 截止下标
     */
    private int endIndex;

    /**
     * 查询数据列表
     */
    private List<T> rows = Collections.emptyList();

    public Pagination() {
    }

    /**
     * 创建分页对象
     *
     * @param pageNum   当前页
     * @param pageSize  每页的记录数
     */
    public Pagination(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }


}
