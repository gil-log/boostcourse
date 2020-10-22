package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    // 모든 카테고리 정보 출력
    public List<CategoryDTO.Categories> findAllCategories();

    // 카테고리 항목 개수 출력
    public Long CategoriesSize();

    // 해당 카테고리 항목 개수 출력
    public Long categoriesCount(Long category_id);
}
