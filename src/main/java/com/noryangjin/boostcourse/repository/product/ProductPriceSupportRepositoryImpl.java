package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.ProductPrice;
import com.noryangjin.boostcourse.domain.QProductPrice;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductPriceSupportRepositoryImpl extends QuerydslRepositorySupport implements ProductPriceSupportRepository {

    public ProductPriceSupportRepositoryImpl(){super(ProductPrice.class);}

    @Override
    public List<ProductPrice> findProductPriceByProductId(long productId) {
        QProductPrice productPrice = QProductPrice.productPrice;

        return from(productPrice)
                .where(productPrice.product_id.eq(productId))
                .orderBy(productPrice.price.asc())
                .fetch();
    }
}
