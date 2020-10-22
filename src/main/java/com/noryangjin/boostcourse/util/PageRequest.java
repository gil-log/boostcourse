package com.noryangjin.boostcourse.util;

import org.springframework.data.domain.Sort;

public class PageRequest {
    private int page;
    private int size;
    private Sort.Direction direction;

    public PageRequest(int start) {
        setPage(start);
        setSize(4);
        setDirection(Sort.Direction.DESC);
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 4;
        int MAX_SIZE = 4;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }
    // getter

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page -1, size);
    }
}
