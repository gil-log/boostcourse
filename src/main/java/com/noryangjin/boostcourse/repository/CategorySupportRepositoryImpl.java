package com.noryangjin.boostcourse.repository;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.domain.QCategory;
import com.noryangjin.boostcourse.domain.QDisplayInfo;
import com.noryangjin.boostcourse.domain.QProduct;
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
                .fetchAll().fetch();
    }

    @Override
    public long CategoriesSize(){
        final QCategory category = QCategory.category;
        return from(category)
                .select(category.count())
                .fetchCount();
    }

    @Override
    public long CategoryCounting(long id) {
        final QCategory category = QCategory.category;
        final QProduct product = QProduct.product;
        final QDisplayInfo displayInfo = QDisplayInfo.displayInfo;

        return from(displayInfo)
                .select(displayInfo.count())
                .where(displayInfo.product_id.in
                        (
                                (from(product)
                                        .select(product.id)
                                        .where(product.category_id.eq(id)))
                        )
                ).fetchCount();
    }
}
