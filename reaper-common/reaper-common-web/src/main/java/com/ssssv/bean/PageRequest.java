package com.ssssv.bean;

import lombok.Setter;

@Setter
public class PageRequest {

    private Long pageIndex = 1L;

    private Long pageSize = 10L;

    public Long getPageIndex() {
        if (pageIndex == null || pageIndex < 1) {
            return 1L;
        }
        return pageIndex;
    }

    public Long getPageSize() {
        if (pageSize == null || pageSize < 1) {
            return 10L;
        }
        return pageSize;
    }
}
