package com.dibragimov.test.stackexchange.dto;

/**
 * Object, containing information about request
 */
public class Query {
    private String title;
    private int pageNum;
    private int pageSize;

    public Query() {
    }

    public Query(String title, int pageNum, int pageSize) {
        this.title = title;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getTitle() {
        return title;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }
}
