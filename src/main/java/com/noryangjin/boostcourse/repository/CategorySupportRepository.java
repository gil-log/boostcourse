package com.noryangjin.boostcourse.repository;

import com.noryangjin.boostcourse.domain.Category;

import java.util.List;

public interface CategorySupportRepository {

    public List<Category> findAllCategories();

}
