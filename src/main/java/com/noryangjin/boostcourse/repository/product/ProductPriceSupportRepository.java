package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.ProductPrice;

import java.util.List;

public interface ProductPriceSupportRepository {

    // productId로 productPrice List 가져오기
    public List<ProductPrice> findProductPriceByProductId(long productId);
}
