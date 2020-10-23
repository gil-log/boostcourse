package com.noryangjin.boostcourse.repository.category;

import com.noryangjin.boostcourse.domain.Category;

import java.util.List;

public interface CategorySupportRepository {

    public List<Category> findAllCategories();

    public long CategoriesSize();

    public long CategoryCounting(long id);

    public Category findById(long category_id);

}
