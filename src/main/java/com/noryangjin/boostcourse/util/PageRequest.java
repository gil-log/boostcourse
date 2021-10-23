package com.noryangjin.boostcourse.util;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

public class PageRequest {
    private int page;
    private int size;
    private Sort.Direction direction;

    public PageRequest(int start, int size) {
        setPage(start);
        setSize(size);
        setDirection(Sort.Direction.DESC);
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = size;
        int MAX_SIZE = size;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public PageRequest of() {
        return of(page -1, size);
    }
}
