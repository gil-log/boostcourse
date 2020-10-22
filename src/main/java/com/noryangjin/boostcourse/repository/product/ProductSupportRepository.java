package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.Product;

public interface ProductSupportRepository {

    public Product findByProductId(long product_id);
}
