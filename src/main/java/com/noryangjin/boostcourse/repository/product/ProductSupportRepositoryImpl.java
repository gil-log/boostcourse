package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.Product;
import com.noryangjin.boostcourse.domain.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ProductSupportRepositoryImpl extends QuerydslRepositorySupport implements ProductSupportRepository {

    public ProductSupportRepositoryImpl(){super(Product.class);}

    @Override
    public Product findByProductId(long product_id) {
        final QProduct qProduct = QProduct.product;

        return from(qProduct)
                .where(qProduct.id.eq(product_id))
                .fetchOne();
    }
}
