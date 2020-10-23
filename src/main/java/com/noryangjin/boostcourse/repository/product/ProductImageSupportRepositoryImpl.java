package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.ProductImage;
import com.noryangjin.boostcourse.domain.QProductImage;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProductImageSupportRepositoryImpl extends QuerydslRepositorySupport implements ProductImageSupportRepository {

    public ProductImageSupportRepositoryImpl(){super(ProductImage.class);}

    @Override
    public ProductImage findByProductIdAndType(long product_id, String type) {
        QProductImage productImage = QProductImage.productImage;

        return from(productImage)
                .where(productImage.product_id.eq(product_id).and(productImage.type.eq(type)))
                .fetchOne();
    }
}
