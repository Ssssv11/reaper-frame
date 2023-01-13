package com.ssssv.bean;

import java.util.Collections;
import java.util.List;

public class PageResponse<T> {

    private Long pageIndex = 1L;

    private Long pageSize = 10L;

    private Long total = 0L;

    private Long pages = 0L;

    private List<T> result = Collections.emptyList();

    private Long start = 1L;

    private Long end = 0L;

    public PageResponse(){}

    public void setResult(List<T> result) {
        this.result = result;
        if (result != null && result.size() > 0 && this.total == 0) {
            setTotal((long) result.size());
        }
    }

    public void setPageIndex(Long pageIndex) {
        if (pageIndex != null && pageIndex > 0) {
            this.pageIndex = pageIndex;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public void setTotal(Long total) {
        this.total = total;
        if (total == -1) {
            this.pageIndex = 1L;
            return;
        }
        if (this.pageSize > 0) {
            this.pages = (total / this.pageSize) + (total % this.pageSize == 0 ? 0 : 1);
        } else {
            this.pages = 0L;
        }
        this.start = (this.pageIndex > 0 ? (this.pageIndex - 1) * this.pageSize : 0) + 1;
        this.end = (this.start - 1 + this.pageSize * (this.pageIndex > 0 ? 1 : 0));
    }
}
