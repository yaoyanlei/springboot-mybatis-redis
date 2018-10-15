package com.neo.common;


import java.util.Collections;
import java.util.List;

/**
 * Created by liding on 2018/8/30.
 */
public class PageDataByCursor<T> {

    private int limit;
    private int next;
    private List<T> data;

    /**
     * 开始的下一条数据的游标
     */
    private long cursor;

    public PageDataByCursor() {
        super();
    }

    public PageDataByCursor(int next, int limit) {
        super();
        this.limit = limit;
        this.next = next;
    }

    public PageDataByCursor(int next, int limit, List<T> data) {
        super();
        this.next = next;
        this.limit = limit;
        this.data = data;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getNext() {
        if (data == null || data.isEmpty() || data.size() < limit) {
            return -1;
        }
        return next + limit;
    }

    public List<T> getData() {
        return data == null ? Collections.emptyList() : data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }

    public long getCursor() {

        if (cursor > 0) return cursor;

        if (data == null || data.isEmpty() || data.size() < limit) {
            return -1;
        }
        return 0;
    }
}
