package com.noryangjin.boostcourse.repository.category;

import com.noryangjin.boostcourse.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategorySupportRepository {

}

interface CategorySupportRepository {

    public List<Category> findAllCategories();

    public long CategoriesSize();

    public long CategoryCounting(long id);

    public Category findById(long category_id);

}