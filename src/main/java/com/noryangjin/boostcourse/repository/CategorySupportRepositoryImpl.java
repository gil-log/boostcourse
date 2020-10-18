package com.noryangjin.boostcourse.repository;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.domain.QCategory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public class CategorySupportRepositoryImpl extends QuerydslRepositorySupport implements CategorySupportRepository {

    public CategorySupportRepositoryImpl(){
        super(Category.class);
    }

    @Override
    public List<Category> findAllCategories() {
        final QCategory category = QCategory.category;
        return from(category)
                .fetch();
    }
}
